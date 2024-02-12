(ns electric-portfolio.tests.tutorial-7guis-scenes
  (:require #?(:cljs [portfolio.dom :refer-macros [defscene]])
            #?(:cljs [goog.dom :as gdom]) ; required for util/electric-node
            [hyperfiddle.electric :as e]
            [electric-portfolio.util :as util]
            [tutorial-7guis.counter]
            [tutorial-7guis.temperature]
            [tutorial-7guis.timer]
            [tutorial-7guis.crud]))

;; 1. Counter
(e/defn Counter []
  (tutorial-7guis.counter/Counter.))
#?(:cljs
   (defscene counter
     :title "1: Counter"
     (util/electric-node Counter)))

;; 2. Temperature converter
(e/defn Temperature []
  (tutorial-7guis.temperature/TemperatureConverter.))
#?(:cljs
   (defscene temperature
     :title "2. Temperature Converter"
     (util/electric-node Temperature)))

;; 4. Timer
(e/defn Timer []
  (tutorial-7guis.timer/Timer.))
#?(:cljs
   (defscene timer
     :title "4. Timer"
     (util/electric-node Timer)))

;; 5. CRUD
(e/defn CRUD []
  (tutorial-7guis.crud/CRUD.))
#?(:cljs
   (defscene crud
     :title "5. CRUD"
     (util/electric-node CRUD)))

;; Aggregate all `e/defn` scenes together
(e/def scenes
  (e/client 
   (util/into-scenes
    [Counter
     Temperature
     Timer
     CRUD])))
