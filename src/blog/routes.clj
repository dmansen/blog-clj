(ns blog.routes
  (:use compojure.core
        blog.views
        blog.sql
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/cool" [] (cool-page))
  (GET "/post/:name" [name] (get-post (:content
                                       (first (get-record :posts [:content] {:name name})))))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))
