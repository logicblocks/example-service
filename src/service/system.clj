(ns service.system
  (:require
    [com.stuartsierra.component :as component]))

(defn new-service []
  (component/system-map))

(defn start [service]
  (component/start-system service))

(defn stop [service]
  (component/stop-system service))
