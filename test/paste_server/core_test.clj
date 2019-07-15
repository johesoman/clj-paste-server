(ns paste-server.core-test
  (:require
    [clojure.test :refer :all]
    [clj-http.client :as http]
    [cheshire.core :as json]
    [paste-server.core :refer :all]))

(defn post-get-body [path data]
  (let [json-data (json/generate-string {:debug true
                                         :data data})
        response (http/post path {:insecure? true
                                  :body json-data})]
    (json/parse-string (:body response) true)))

(deftest a-test
  (testing "POST to store."
    (is (= (post-get-body "https://localhost:3443/store/hej" "hejsan!")
           {:where "hej"
            :data "hejsan!"}))))
