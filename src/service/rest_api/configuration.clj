(ns service.rest-api.configuration
  (:require
   [configurati.core
    :refer [define-configuration-specification
            define-configuration
            with-specification
            with-parameter
            with-source
            with-key-fn
            env-source]]
   [configurati.key-fns
    :refer [remove-prefix]]))

(defn- seconds->millis [seconds]
  (* seconds 1000))

(def specification
  (define-configuration-specification
    (with-key-fn (remove-prefix :rest-api))
    (with-parameter :rest-api-bind-ip :default "0.0.0.0")
    (with-parameter :rest-api-host :default "localhost")
    (with-parameter :rest-api-port :default 3000 :type :integer)
    (with-parameter :rest-api-shutdown-timeout
      :default (seconds->millis 10)
      :type :integer)))

(def definition
  (define-configuration
    (with-specification specification)
    (with-source (env-source :prefix :example-service))))
