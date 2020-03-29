(ns service.rest-api.handlers
  (:require
   [bidi.ring :as bidi-ring]

   [ring.middleware.params :as params]
   [ring.middleware.keyword-params :as keyword-params]
   [ring.middleware.content-type :as content-type]
   [ring.middleware.not-modified :as not-modified]
   [ring.middleware.ssl :as ssl]

   [liberator-hal.discovery-resource.core
    :as discovery-resource]
   [liberator-hal.ping-resource.core
    :as ping-resource]

   [service.rest-api.routing :as routing]))

(defn resources [dependencies]
  {:discovery (discovery-resource/handler dependencies)
   :ping      (ping-resource/handler dependencies)})

(defn main [dependencies]
  (let [routes (routing/routes)
        dependencies (merge {:routes routes} dependencies)
        resource-handlers (resources dependencies)]
    (-> (bidi-ring/make-handler routes resource-handlers)

      (ssl/wrap-forwarded-scheme)
      (content-type/wrap-content-type)
      (not-modified/wrap-not-modified)

      (keyword-params/wrap-keyword-params)
      (params/wrap-params))))
