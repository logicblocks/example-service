(ns service.component-test-support.system
  (:require
    [freeport.core :refer [get-free-port!]]

    [service.shared.logging :refer [initialise]]
    [service.system :as system]

    [service.component-test-support.rest-api :as rest-api]))

(defn create [& {:as configuration}]
  (let [host "localhost"
        port (get-free-port!)]
    (system/map
      (merge
        {:rest-api (rest-api/configuration host port)}
        configuration))))

(defn address [system]
  (rest-api/address (:rest-api system)))

(defn with-lifecycle [system-atom]
  (fn [f]
    (try
      (initialise)
      (swap! system-atom system/start)
      (f)
      (finally
        (swap! system-atom system/stop)))))
