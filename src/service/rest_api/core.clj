(ns service.rest-api.core
  (:require
    [service.rest-api.component :as component]
    [service.rest-api.configuration :as configuration]))

(def configuration
  configuration/definition)

(defn component []
  (component/map->RestApi {}))
