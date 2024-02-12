An example repo for combining [portfolio](https://github.com/cjohansen/portfolio) and [electric](https://github.com/hyperfiddle/electric).

# Run the demo
* Shell: `clj -A:dev -X dev.electric-entry/-main` or REPL: `(dev.electric-entry/-main)`
* http://localhost:8080

# Issues
* Splitting electric scenes into multiple panes eventually fails to mount the components properly (e.g., after 10 splits).
`#object[DOMException NotFoundError: Failed to execute 'insertBefore' on 'Node': The node before which the new node is to be inserted is not a child of this node.]`
Hitting this error causes it to be more likely to occur again with fewer splits (e.g., occurs again splitting from 1 pane to 2-4). Refreshing the page resets the issue.
* The default portfolio viewport automatically determines height/width. For scenes like electric-tutorial '3. System Properties' this can result in a tiny scroll window (presumably due to the delay in populating the table). You can manually fix this with a custom viewport configuration (`:viewport/options` in `electric-portfolio.app`) or rendering the scene in a container with a set height (see `fixed-height-container` in `electric-portfolio.tests.general-scenes`). An automatic solution is left as an exercise to the reader. 
* electric-tutorial '7. Chat Extended' `e/http-request` is `nil` so `username` is never retrieved.

# Web component
A custom web component defined in `src/util.cljc` uses connect/disconnect events to trigger mounting/unmounting of electric components. The component is created using my fork of [zero](https://github.com/raystubbs/zero/tree/main) (only difference is commenting out setting of adoptedStytleSheets on the node, which was generating an error for this use case). A custom component can also be created without the extra dependency like so (though it is less friendly to hot reloads):
```clojure
(ns electric-portfolio.util
  #?(:cljs (:require [shadow.cljs.modern :refer (defclass)]))

;; ...

#?(:cljs
   (defn add-node! [node]
     (let [id (.-id node)
           win ^js (.-defaultView (.getRootNode node))]
       (swap! !nodes assoc id node)
       (set! (.-onpagehide win) #(.remove node)))))

#?(:cljs
   (defclass ElectricNode
     (extends js/HTMLElement)
     (constructor [this] (super))
     Object
     (connectedCallback [this] (add-node! this))
     (disconnectedCallback [this] (swap! !nodes dissoc this.id))))

#?(:cljs (js/customElements.define "electric-node" ElectricNode))
```
In both versions, setting the `onpagehide` event ensures proper unmounting of `electric-node`'s when working with split views of scenes.