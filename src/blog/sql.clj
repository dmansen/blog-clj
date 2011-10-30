(ns blog.sql
  (:require [clojure.java.jdbc :as sql]))

; Give the sql library all the information about the db
; to be used in transactions
(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"         ; Protocol to use
         :subname "db/db.sqlite3"      ; Location of db
         :create true})

(. Class (forName "org.sqlite.JDBC")) ; Initialize the JDBC driver

(defn make-where
  [record]
  (if (empty? record)
    ""
    (loop [add-and true
           rec record
           current-query " WHERE "]
      (if (empty? rec)
        current-query
        (let [[key val] (first rec)]
          (recur true
                 (next rec)
                 (str
                  current-query
                  (if add-and
                    ""
                    " AND ")
                  " "
                  (name key)
                  " = "
                  (if (string? val)
                    (str "\"" val "\"")
                    val))))))))

(defn make-fields
  [fields]
  (loop [add-comma false
         rec fields
         current-query ""]
    (if (empty? rec)
      current-query
      (recur true
             (next rec)
             (str current-query (if add-comma
                    ","
                    "")
                  (name (first rec)))))))

(defn db-create
  "Creates the table for this model"
  []
  (sql/with-connection
    db
    (sql/create-table
     :posts
     [:id :integer "PRIMARY KEY"]
     [:name "varchar(128)"]
     [:content "text"])))

(defn table-drop
  "Drops the table"
  [table]
  (sql/with-connection
    db
    (sql/drop-table
     (keyword table))))

(defn get-record
  [table fields record]
  (sql/with-connection
    db
    (sql/transaction
     (sql/with-query-results res
       [(str "SELECT " (make-fields fields) " FROM " (name table) (make-where record))]
       (into [] res)))))

(defn create-post
  [record]
  (sql/with-connection
    db
    (sql/insert-record
     :posts
     record)))