(ns dev.electric-entry
  (:require #?(:clj util.server-jetty)
            #?(:clj [clojure.tools.logging :as log])
            #?(:clj [shadow.cljs.devtools.api :as shadow])
            #?(:clj [shadow.cljs.devtools.server :as shadow-server])
            [hyperfiddle.electric :as e]
            [electric-portfolio.app :as app]))

(comment (-main)) ; repl entrypoint

#?(:clj ;; Server Entrypoint
   (do
     (def config
       {:host "0.0.0.0"
        :port 8080
        :resources-path "public"
        :manifest-path "public/js/manifest.edn"})
     
     (defn -main [& args]
       (log/info "Starting Electric compiler and server...")

       (shadow-server/start!)
       (shadow/watch :dev)
       (comment (shadow-server/stop!))

       (def server (util.server-jetty/start-server!
                    (fn [ring-request]
                      (e/boot-server {} app/App ring-request))
                    config))
       (comment (.stop server))
       )))

#?(:cljs ;; Client Entrypoint
   (do
     (def electric-entrypoint (e/boot-client {} app/App nil))

     (defonce reactor nil)

     (defn ^:dev/after-load ^:export start! []
       (set! reactor (electric-entrypoint
                      #(js/console.log "Reactor success:" %)
                      #(js/console.error "Reactor failure:" %))))

     (defn ^:dev/before-load stop! []
       (when reactor (reactor))
       (set! reactor nil))))
