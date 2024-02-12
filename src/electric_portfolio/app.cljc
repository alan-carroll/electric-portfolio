(ns electric-portfolio.app
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            #?(:cljs [portfolio.ui])
            [electric-portfolio.util :as util]
            [electric-portfolio.tests.general-scenes]
            [electric-portfolio.tests.electric-tutorial-scenes]))

(e/def scenes
  (merge
   {}
   electric-portfolio.tests.general-scenes/scenes
   electric-portfolio.tests.electric-tutorial-scenes/scenes))

(def portfolio-config
  {:config
   {:viewport/options
    [{:title "Fixed height"
      :value {:viewport/height "800px"}}]
    :css-paths ["/css/test.css"]}})

(e/defn Scene [^js node]
  (e/client
   (binding [dom/node (.-parentNode node)]
     (let [scene (.-scene node)
           program (get scenes scene)]
       (new program)
      ;;  (e/on-unmount #(println "Unmounted" scene (.-id node)))
       ))))

(e/defn App [ring-request]
  (e/client
   (binding [dom/node js/document.body]
     (portfolio.ui/start! portfolio-config)
     (let [nodes (e/watch util/!nodes)]
       (e/for-by key [[id node] nodes]
         (Scene. node))))))
