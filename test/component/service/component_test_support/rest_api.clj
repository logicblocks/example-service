(ns service.component-test-support.rest-api
  (:require
    [configurati.core
     :refer [define-configuration
             with-specification
             with-source
             with-key-fn
             yaml-file-source
             map-source
             env-source]]
    [configurati.key-fns
     :refer [remove-prefix]]

    [service.rest-api.configuration :as configuration]))

(defn address [service]
  (:address service))

(defn configuration [host port]
  (define-configuration
    (with-specification configuration/specification)
    (with-source
      (map-source {:rest-api-host host
                   :rest-api-port port}))))
