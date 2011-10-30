(defproject compojure-example "0.1.0"
  :description "Example Compojure project"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/java.jdbc "0.1.0"]
                 [compojure "0.6.4"]
                 [hiccup "0.3.6"]
                 [rhino/js "1.7R2"]
                 [org.xerial/sqlite-jdbc "3.6.16"]]
  :dev-dependencies [[lein-ring "0.4.5"]]
  :ring {:handler blog.routes/app})
