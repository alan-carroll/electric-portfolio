(ns electric-portfolio.tests.general-scenes
  (:require #?(:cljs [portfolio.dom :refer-macros [defscene]])
            #?(:cljs [goog.dom :as gdom]) ; required for util/electric-node
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [electric-portfolio.tests.components :as c]
            [electric-portfolio.util :as util]))

;; Simple non-electric scene
#?(:cljs
   (defscene hello-world
     (let [h1 (gdom/createElement "h1")]
       (gdom/setTextContent h1 "Hello world!")
       h1)))

;; Simple electric scene
(e/defn Electric-hello []
  (e/client (dom/h1 (dom/text "Hello electric!"))))
#?(:cljs
   (defscene electric-hello
     (util/electric-node Electric-hello)))

;; Use 'components' to build electric scene
(e/defn Electric-components []
  (c/Great-h1.)
  (c/Great-button.)
  (e/on-unmount #(println "Greatness is gone, but not forgotten...")))
#?(:cljs
   (defscene greatness
     (util/electric-node Electric-components)))

;; electric-nodes can be added to non-electric nodes
(e/defn ContainerTest []
  (e/client 
   (dom/h1 
    (dom/text "This electric component is at the top of an extra tall div."))
   (dom/h1 
    (dom/text "This component is way down at the bottom!")
    (dom/props {:style {:padding-top "800px"}}))))
#?(:cljs
   (defscene fixed-height-container
     (let [div (gdom/createElement "div")
           electric-node (util/electric-node ContainerTest)]
       (set! (.-height (.-style div)) "1000px")
       (.appendChild div electric-node)
       div)))

;; Aggregate all `e/defn` scenes together
(e/def scenes
  (e/client 
   (util/into-scenes [Electric-hello
                      Electric-components
                      ContainerTest])))
