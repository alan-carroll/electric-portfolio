(ns electric-portfolio.tests.components
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

(e/defn Great-h1 []
  (e/client
   (dom/h1 (dom/text "Greatness awaits you..."))))

(e/defn Great-button []
  (e/client
   (ui/button (e/fn [] (println "GREATNESS BE UPON YOU"))
              (dom/text "PRESS ME FOR GREATNESS"))))
