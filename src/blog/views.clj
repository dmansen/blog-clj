(ns blog.views
  (:use [hiccup core page-helpers]
        [blog markdown]))

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

(def base-dir "/home/derek/code/clojure/blog-clj")

(defn get-post [file]
  (let [text (slurp (str "resources/public/posts/" file ".md"))]
    (html5
     [:head
      [:title "A Post"]
      (include-css "/css/style.css")
      (include-js jquery-file)
      (include-js "/js/main.js")]
     [:body
      (markdown-to-html text true)])))