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
    " 1 = 1"
    (loop [rec record
           current-query ""]
      (if (empty? rec)
        current-query
        (let [[key val] (first rec)]
          (recur (next rec)
                 (str
                  current-query
                  (if (empty? current-query)
                    ""
                    " AND ")
                  " "
                  (name key)
                  " = "
                  (if (string? val)
                    (str "\"" val "\"")
                    val))))))))

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
  [table record]
  (sql/with-connection
    db
    (sql/transaction
     (sql/with-query-results res
       [(str "SELECT * FROM " (name table) " WHERE " (make-where record))]
       (into [] res)))))

(defn create-post
  [record]
  (sql/with-connection
    db
    (sql/insert-record
     :posts
     record)))