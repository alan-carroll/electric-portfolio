(ns electric-portfolio.tests.electric-tutorial-scenes
  (:require #?(:cljs [portfolio.dom :refer-macros [defscene]])
            #?(:cljs [goog.dom :as gdom]) ; required for util/electric-node
            [hyperfiddle.electric :as e]
            [electric-portfolio.util :as util]
            [electric-tutorial.demo-two-clocks]
            [electric-tutorial.demo-toggle]))

;; 1. Two clocks
(e/defn TwoClocks []
  (electric-tutorial.demo-two-clocks/TwoClocks.))
#?(:cljs
   (defscene two-clocks
     :title "1. Two Clocks"
     (util/electric-node TwoClocks)))

;; 2. Toggle
(e/defn Toggle []
  (electric-tutorial.demo-toggle/Toggle.))
#?(:cljs
   (defscene toggle
     :title "2. Toggle"
     (util/electric-node Toggle)))

(e/def scenes
  (e/client 
   (util/into-scenes [TwoClocks
                      Toggle])))
