(ns blog.sql
  (:require [clojure.contrib.sql :as sql]))

; Give the sql library all the information about the db
; to be used in transactions
(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"         ; Protocol to use
         :subname "db/db.sqlite3"      ; Location of db
         :create true})

(. Class (forName "org.sqlite.JDBC")) ; Initialize the JDBC driver

(defn db-create
  "Creates the table for this model"
  []
  (sql/create-table
   :something
   [:id :int "PRIMARY KEY"]
   [:name "varchar(32)"]))

(sql/with-connection
  db
  (sql/transaction
   (db-create)))