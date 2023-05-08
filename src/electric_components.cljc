(ns electric-components
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

(e/defn Electric-h1 []
  (dom/h1 (dom/text "This is an electric H1")))

(e/defn Electric-button []
  (ui/button (e/fn [] (println "GLORY TO YOU")) (dom/text "PRESS ME FOR GLORY")))
