(ns service.rest-api.routing)

(defn routes []
  [""
   [["/" :discovery]
    ["/ping" :ping]]])
