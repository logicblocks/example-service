(ns service.shared.logging
  (:require
    [cambium.codec :as codec]
    [cambium.logback.json.flat-layout :as flat])
  (:import [org.slf4j.bridge SLF4JBridgeHandler]))

(defn initialise []
  (do
    (flat/set-decoder! codec/destringify-val)
    (SLF4JBridgeHandler/removeHandlersForRootLogger)
    (SLF4JBridgeHandler/install)))
