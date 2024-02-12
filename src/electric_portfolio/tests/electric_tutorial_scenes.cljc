(ns electric-portfolio.tests.electric-tutorial-scenes
  (:require #?(:cljs [portfolio.dom :refer-macros [defscene]])
            #?(:cljs [goog.dom :as gdom]) ; required for util/electric-node
            [hyperfiddle.electric :as e]
            [electric-portfolio.util :as util]
            [electric-tutorial.demo-two-clocks]
            [electric-tutorial.demo-toggle]
            [electric-tutorial.demo-system-properties]
            [electric-tutorial.demo-chat]
            [electric-tutorial.demo-backpressure]
            [electric-tutorial.demo-lifecycle]
            [electric-tutorial.demo-chat-extended]
            [electric-tutorial.demo-webview]
            [electric-tutorial.demo-todos-simple]
            [electric-tutorial.demo-svg]))

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

;; 3. System Properties
(e/defn SystemProperties []
  (electric-tutorial.demo-system-properties/SystemProperties.))
#?(:cljs
   (defscene system-properties
     :title "3. System Properties"
     (util/electric-node SystemProperties)))

;; 4. Chat
(e/defn Chat []
  (electric-tutorial.demo-chat/Chat.))
#?(:cljs
   (defscene chat
     :title "4. Chat"
     (util/electric-node Chat)))

;; 5. Backpressure
(e/defn Backpressure []
  (electric-tutorial.demo-backpressure/Backpressure.))
#?(:cljs
   (defscene backpressure
     :title "5. Backpressure"
     (util/electric-node Backpressure)))

;; 6. Lifecycle
(e/defn Lifecycle []
  (electric-tutorial.demo-lifecycle/Lifecycle.))
#?(:cljs
   (defscene lifecycle
     :title "6. Component Lifecycle"
     (util/electric-node Lifecycle)))

;; 7. Chat Extended
(e/defn ChatExtended []
  (electric-tutorial.demo-chat-extended/ChatExtended.))
#?(:cljs
   (defscene chat-extended
     :title "7. Chat Extended"
     (util/electric-node ChatExtended)))

;; 8. Webview
(e/defn Webview []
  (electric-tutorial.demo-webview/Webview.))
#?(:cljs
   (defscene webview
     :title "8. Webview"
     (util/electric-node Webview)))

;; 9. Todos Simple
(e/defn TodoList []
  (electric-tutorial.demo-todos-simple/TodoList.))
#?(:cljs
   (defscene todos-simple
     :title "9. Todos Simple"
     (util/electric-node TodoList)))

;; 10. SVG
(e/defn SVG []
  (electric-tutorial.demo-svg/SVG.))
#?(:cljs
   (defscene svg
     :title "10. SVG"
     (util/electric-node SVG)))

(e/def scenes
  (e/client 
   (util/into-scenes
    [TwoClocks
     Toggle
     SystemProperties
     Chat
     Backpressure
     Lifecycle
     ChatExtended
     Webview
     TodoList
     SVG])))
