(ns service.ping-resource-test
  (:require
    [clojure.test :refer :all]

    [halboy.resource :as hal]
    [halboy.navigator :as navigator]

    [clojure.string :refer [ends-with?]]
    [org.bovinegenius.exploding-fish :refer [absolute?]]

    [service.component-test-support.system :as system]))

(let [test-system (atom (system/create))]
  (use-fixtures :once (system/with-lifecycle test-system))

  (deftest ping-resource-GET-on-success
    (let [address (system/address @test-system)

          result (-> (navigator/discover address)
                   (navigator/get :ping))
          resource (navigator/resource result)]
      (testing "returns status code 200"
        (is (= 200 (navigator/status result))))

      (testing "includes a self link"
        (let [self-href (hal/get-href resource :self)]
          (is (absolute? self-href))
          (is (ends-with? self-href "/ping"))))

      (testing "includes a link to discovery"
        (let [discovery-href (hal/get-href resource :discovery)]
          (is (absolute? discovery-href))
          (is (ends-with? discovery-href "/"))))

      (testing "returns a pong message"
        (is (= "pong" (hal/get-property resource :message)))))))
