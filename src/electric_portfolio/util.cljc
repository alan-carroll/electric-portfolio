(ns electric-portfolio.util
  #?(:cljs (:require goog.dom
                     [zero.core :as z] ; required for zc/reg-components?
                     [zero.config :as zc]))
  #?(:cljs (:require-macros [electric-portfolio.util])))

(def !nodes (atom {}))

#?(:cljs
   (defn add-node! [event id]
     (let [node ^js (.-host (.-target event))
           win ^js (.-defaultView (.getRootNode node))]
       (swap! !nodes assoc id node)
       (set! (.-onpagehide win) #(.remove node)))))

#?(:cljs
   (defn node-view [{:keys [id]}]
     [:root>
      :zero.core/on {:connect #(add-node! % id)
                     :disconnect #(swap! !nodes dissoc id)}]))

#?(:cljs
   (zc/reg-components
    :electric/node
    {:props #{:id :scene}
     :view node-view}))

(defmacro qualify
  "Return a fully qualified symbol. `sym` can be quoted or unquoted.
   `?quote` indicates if return should be quoted or not; default is `true`."
  [sym & [?quote]]
  (let [?quote (if (nil? ?quote) true ?quote)
        val (if (and (seq? sym) (= (first sym) 'quote))
              (second sym)
              sym)
        n (if (:ns &env) (get-in &env [:ns :name]) *ns*)
        full (str n "/" val)]
    (if ?quote `(quote ~full) (symbol full))))

(defmacro electric-node
  "Creates a new `ElectricNode` custom DOM element for use with 
   `portfolio.dom/defscene`.
   `body` should be the name of an `e/defn`. It can be quoted or bare."
  [body]
  (assert (:ns &env))
  `(let [id# (str (random-uuid))
         scene# (qualify ~body)
         elem# (goog.dom/createElement "electric-node")]
     (set! (.-id elem#) id#)
     (set! (.-scene elem#) scene#)
     elem#))

;; https://github.com/hyperfiddle/electric-examples-app/blob/main/src/user_main.cljc
(defmacro into-scenes
  "Returns a map of fully qualified names from collection in `body`, repeating
   a quoted symbol as key and unquoted as value (the unquoted version is
   necessary for `electric` compilation)."
  [body]
  (into {} (map #(vector `(qualify ~%) `(qualify ~% false))) body))