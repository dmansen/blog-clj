(ns blog.views
  (:use [hiccup core page-helpers]
        [blog sql]))

; Current version of jquery
(def jquery-file
  "/js/jquery-1.6.4.js")

(defn index-page []
  (html5
   [:head
    [:title "Hello World"]
    (include-css "/css/style.css")
    (include-js jquery-file)]
   [:body
    [:h1 "Hello World"]]))

(defn cool-page []
  (html5
   [:head
    [:title "This is the coolest page"]
    (include-css "/css/style.css")
    (include-js jquery-file)
    (include-js "/js/main.js")]
   [:body
    [:h1 {:class "huger"} "The coolest page"]
    [:p "This is a paragraph! Built in clojure!"]]))

(defn get-post [post-text]
  (html5
   [:head
    [:title "A Post"]
    (include-css "/css/style.css")
    (include-js jquery-file)
    (include-js "/js/showdown.js")
    (include-js "/js/main.js")
    (include-js "/js/post.js")]
   [:body
    [:div {:class "markdown"}
     post-text]]))