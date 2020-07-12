(ns service.rest-api.component
  (:require
   [com.stuartsierra.component :as component]

   [org.httpkit.server :as server]

   [service.rest-api.handlers :as handlers]))

(defn- format-address [host port]
  (format "http://%s:%s" host port))

(defrecord RestApi
  [configuration
   server]
  component/Lifecycle

  (start [component]
    (let [{:keys [bind-ip host port]} configuration
          handler (handlers/main {})
          server (server/run-server handler
                   {:ip   bind-ip
                    :port port})]
      (assoc component
        :server server
        :address (format-address host port))))

  (stop [component]
    (let [{:keys [shutdown-timeout]} configuration
          server (:server component)]
      (when server
        (server :timeout shutdown-timeout))
      (dissoc component :server :address))))
