(ns service.system
  (:require
   [com.stuartsierra.component :as component]

   [configurati.core :as config]

   [service.rest-api.core :as rest-api]))

(defn create
  ([] (create {}))
  ([configuration-overrides]
   (component/system-map
     :rest-api-configuration
     (config/resolve
       (:rest-api configuration-overrides rest-api/configuration))

     :rest-api
     (component/using
       (rest-api/create)
       {:configuration :rest-api-configuration}))))

(defn start [service]
  (component/start-system service))

(defn stop [service]
  (component/stop-system service))
