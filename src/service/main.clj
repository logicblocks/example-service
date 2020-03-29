(ns service.main
  (:require
   [service.system :as system]
   [service.shared.logging :refer [setup-logging]])
  (:gen-class))

(defn -main [& _]
  (do
    (setup-logging)
    (let [system (system/start (system/create))]
      (.addShutdownHook
       (Runtime/getRuntime)
       (new Thread (fn* [] (system/stop system)))))))
