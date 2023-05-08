(ns test-scenes
  (:require [portfolio.dom :refer-macros [defscene]]
            [goog.dom :as gdom]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [electric-components]
            [misc]))

;; https://github.com/hyperfiddle/electric-examples-app/blob/main/src/user_main.cljc
(e/def e-scenes {`test-scenes/Electric-hello test-scenes/Electric-hello
                 `test-scenes/Electric-full-scene test-scenes/Electric-full-scene
                 `test-scenes/Electric-counter test-scenes/Electric-counter})

;; Simple non-electric scene
(defscene DOM-hello
  (let [h1 (gdom/createElement "h1")]
    (gdom/setTextContent h1 "Hello world!")
    h1))


;; Electric-based, non-reactive scene
(e/defn Electric-hello []
  (dom/h1 (dom/text "Hello electric!")))

(defscene electric-hello
  (let [d (gdom/createElement "div")]
    (reset! misc/!Scene {:div d :scene `test-scenes/Electric-hello :!state nil})
    d))


;; Using 'components' to build electric scene
(e/defn Electric-full-scene []
  (electric-components/Electric-h1.)
  (electric-components/Electric-button.))

(defscene electric-full-scene
  (let [d (gdom/createElement "div")]
    (reset! misc/!Scene {:div d :scene `test-scenes/Electric-full-scene :!state nil})
    d))


;; Testing 7GUIs Counter
(e/defn Electric-counter [!state]
  (dom/p (dom/text @!state))
  (ui/button (e/fn [] (swap! !state inc)) (dom/text "Count")))

(defscene counter
  :param (atom 0)
  [!state]
  (let [d (gdom/createElement "div")]
    (reset! misc/!Scene {:div d :scene `test-scenes/Electric-counter :!state !state})
    d))
