(ns app.app
  (:require #?(:cljs [portfolio.ui])
            [misc]
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [test-scenes]))

(e/def Scene (e/client (e/watch misc/!Scene)))
(e/def e-scenes)

(e/defn App []
  #?(:cljs (portfolio.ui/start!))
  (e/client
   (binding [e-scenes test-scenes/e-scenes]
     (when (:div Scene)
       (let [d (:div Scene)
             scene (:scene Scene)
             !state (:!state Scene)
             program (get e-scenes scene)]
       ;; Load scenes through UI interaction
         (binding [dom/node d]
           (js/console.log ":div " d)
           (js/console.log ":scene " scene)
           (js/console.log program)
           (when !state (js/console.log ":!state " (clj->js @!state)))
           (dom/div (if !state (new program !state) (new program))))
       ;; Counter example embedded directly inside App
       ;; Selecting electric scenes through UI updates !Scene, but otherwise doesn't load their components
       ;; Selecting non-electric scene loads normally
         #_(binding [dom/node d]
             (let [!count (atom 0)
                   count (e/watch !count)]
               (js/console.log ":div " d)
               (js/console.log ":scene " scene)
               (when !state (js/console.log ":!state " (clj->js @!state)))
               (js/console.log "count: " count)
               (dom/div
                (dom/p (dom/text count))
                (ui/button (e/fn [] (swap! !count inc)) (dom/text "Count")))))
         )))))
