(ns ^:dev/always user
  (:require [hyperfiddle.electric :as e]
            [app.app :as app]))

(def main
  (e/boot
   (app/App.)))

(defonce reactor nil)

(defn ^:dev/after-load ^:export start! []
  (assert (nil? reactor) "reactor already running")
  (set! reactor (main
                 #(js/console.log "Reactor success:" %)
                 #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (when reactor (reactor)) ; teardown
  (set! reactor nil))
