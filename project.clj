(def version
  (or (System/getenv "VERSION")
    "0.0.0+LOCAL"))

(defproject io.logicblocks/example-service version
  :description "Example Service"
  :url "https://github.com/logicblocks/example-service"

  :license "MIT License"

  :dependencies [[org.clojure/clojure "1.10.1"]

                 [bidi "2.1.6"]
                 [http-kit "2.3.0"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-ssl "0.3.0"]

                 [com.stuartsierra/component "1.0.0"]

                 [io.logicblocks/configurati "0.5.2"]
                 [io.logicblocks/liberator-hal.ping-resource "0.1.2-RC4"]
                 [io.logicblocks/liberator-hal.health-resource "0.1.3-RC5"]
                 [io.logicblocks/liberator-hal.discovery-resource "0.1.0-RC6"]

                 [cambium/cambium.core "0.9.3"]
                 [cambium/cambium.codec-cheshire "0.9.3"]
                 [cambium/cambium.logback.core "0.4.3"]
                 [cambium/cambium.logback.json "0.4.3"]

                 [org.slf4j/slf4j-api "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/log4j-over-slf4j "1.7.30"]
                 [ch.qos.logback/logback-classic "1.2.3"
                  :exclusions [org.slf4j/slf4j-api
                               org.slf4j/slf4j-log4j12]]]

  :plugins [[lein-eftest "0.5.9"]
            [lein-ancient "0.6.15"]
            [lein-kibit "0.1.6"]
            [lein-cljfmt "0.6.7"]
            [lein-bikeshed "0.5.1"]
            [jonase/eastwood "0.3.3"]
            [venantius/yagni "0.1.6"]]

  :profiles {:shared      {:dependencies
                           [[nrepl "0.6.0"]
                            [org.clojure/tools.namespace "1.0.0"]
                            [com.stuartsierra/component.repl "0.2.0"]

                            [org.bovinegenius/exploding-fish "0.3.6"]

                            [freeport "1.0.0"]

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

  :cljfmt {:indents {#".*"     [[:inner 0]]
                     defrecord [[:block 1] [:inner 1]]}}

  :aliases {"test" ["do"
                    ["with-profile" "unit" "eftest" ":all"]
                    ["with-profile" "integration" "eftest" ":all"]
                    ["with-profile" "persistence" "eftest" ":all"]
                    ["with-profile" "component" "eftest" ":all"]]})
