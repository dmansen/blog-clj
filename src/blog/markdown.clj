(ns blog.markdown
  (:use (clojure.contrib str-utils shell-out))
  (:import (org.mozilla.javascript Context ScriptableObject)))

(defn- tempfile []
  (java.io.File/createTempFile "cowblog" ""))

(defmacro with-tmp-file [[file text] & rest]
  `(let [tmp# (tempfile)]
     (try
       (with-open [f# (java.io.FileWriter. tmp#)]
         (.append f# ~text))
       (let [~file (.getAbsolutePath tmp#)]
         ~@rest)
       (finally
        (.delete tmp#)))))

;; NOTE: Edit to point to showdown.js

(defn markdown-to-html [txt safe]
  (let [cx (Context/enter)
        scope (.initStandardObjects cx)
        input (Context/javaToJS txt scope)
        script (str (slurp "/home/derek/code/clojure/blog-clj/resources/public/js/showdown.js")
                    "new Showdown.converter().makeHtml(input, " (if safe "true" "false") ");")]
    (try
      (ScriptableObject/putProperty scope "input" input)
      (let [result (.evaluateString cx scope script "<cmd>" 1 nil)]
        (Context/toString result))
      (finally (Context/exit)))))

