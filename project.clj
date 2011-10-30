(defproject compojure-example "0.1.0"
  :description "Example Compojure project"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.1.0"]
                 [compojure "0.6.4"]
                 [hiccup "0.3.6"]
                 [rhino/js "1.7R2"]
                 [sandbar/sandbar "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.6.16"]]
  :dev-dependencies [[lein-ring "0.4.5"]]
  :ring {:handler blog.routes/app})
