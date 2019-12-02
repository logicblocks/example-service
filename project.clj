(def version
  (or (System/getenv "VERSION")
    "0.0.0+LOCAL"))

(defproject io.logicblocks/example-service version
  :description "Example Service"
  :url "https://github.com/logicblocks/example-service"

  :license "MIT License"

  :dependencies [[org.clojure/clojure "1.10.1"]

                 [com.stuartsierra/component "0.4.0"]

                 [cambium/cambium.core "0.9.3"]
                 [cambium/cambium.codec-cheshire "0.9.3"]
                 [cambium/cambium.logback.core "0.4.3"]
                 [cambium/cambium.logback.json "0.4.3"]

                 [org.slf4j/slf4j-api "1.7.29"]
                 [org.slf4j/jcl-over-slf4j "1.7.29"]
                 [org.slf4j/jul-to-slf4j "1.7.29"]
                 [org.slf4j/log4j-over-slf4j "1.7.29"]
                 [ch.qos.logback/logback-classic "1.2.3"
                  :exclusions [org.slf4j/slf4j-api
                               org.slf4j/slf4j-log4j12]]]

  :plugins [[lein-eftest "0.5.9"]]

  :profiles {:shared      {:dependencies
                           [[nrepl "0.6.0"]
                            [org.clojure/tools.namespace "0.3.1"]
                            [com.stuartsierra/component.repl "0.2.0"]

                            [eftest "0.5.9"]]
                           :resource-paths
                           ["test_resources"]}
             :dev         [:shared {:source-paths ["dev"]}]
             :unit        [:shared {:test-paths ^:replace ["test/shared"
                                                           "test/unit"]}]
             :integration [:shared {:test-paths ^:replace ["test/shared"
                                                           "test/integration"]
                                    :eftest     {:multithread? false}}]
             :persistence [:shared {:test-paths ^:replace ["test/shared"
                                                           "test/persistence"]
                                    :eftest     {:multithread? false}}]
             :component   [:shared {:test-paths ^:replace ["test/shared"
                                                           "test/component"]
                                    :eftest     {:multithread? false}}]
             :uberjar     {:main service.main
                           :aot  :all}
             :server      {:main service.main
                           :aot  :all}}
  :target-path "build/app/%s/"
  :test-paths ["test/shared"
               "test/unit"
               "test/persistence"
               "test/integration"
               "test/component"]

  :aliases {"test" ["do"
                    ["with-profile" "unit" "eftest" ":all"]
                    ["with-profile" "integration" "eftest" ":all"]
                    ["with-profile" "persistence" "eftest" ":all"]
                    ["with-profile" "component" "eftest" ":all"]]})
