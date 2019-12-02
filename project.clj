(def version
  (or (System/getenv "VERSION")
    "0.0.0+LOCAL"))

(defproject io.logicblocks/example-service version
  :description "Example Service"
  :url "https://github.com/logicblocks/example-service"

  :license "MIT License"

  :dependencies [[org.clojure/clojure "1.10.1"]]
  :target-path "build/app/%s/")
