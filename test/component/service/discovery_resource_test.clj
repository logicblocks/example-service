(ns service.discovery-resource-test
  (:require
   [clojure.test :refer :all]

   [halboy.resource :as hal]
   [halboy.navigator :as navigator]

   [org.bovinegenius.exploding-fish :refer [absolute?]]
   [clojure.string :refer [ends-with?]]

   [service.component-test-support.system :as system]))

(let [test-system (atom (system/create))]
  (use-fixtures :once (system/with-lifecycle test-system))

  (deftest discovery-resource-GET-on-success
    (let [address (system/address @test-system)
          result (navigator/discover address)
          resource (navigator/resource result)]
      (testing "returns status code 200"
        (is (= 200 (navigator/status result))))

      (testing "includes a self link"
        (let [self-link (hal/get-href resource :self)]
          (is (absolute? self-link))
          (is (ends-with? self-link "/"))))

      (testing "includes a link to discovery"
        (let [discovery-link (hal/get-href resource :discovery)]
          (is (absolute? discovery-link))
          (is (ends-with? discovery-link "/"))))

      (testing "includes a link to ping"
        (let [ping-link (hal/get-href resource :ping)]
          (is (absolute? ping-link))
          (is (ends-with? ping-link "/ping")))))))
