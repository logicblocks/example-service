(ns service.main
  (:require
    [service.system :as system]
    [service.shared.logging :as logging])
  (:gen-class))

(defn -main [& _]
  (do
    (logging/initialise)
    (let [system (system/start (system/map))]
      (.addShutdownHook
        (Runtime/getRuntime)
        (new Thread (fn* [] (system/stop system)))))))
