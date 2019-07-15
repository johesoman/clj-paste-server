(ns paste-server.views
  (:require [environ.core :as environ]
            [hiccup.core :refer :all]
            [clojure.string :refer [join]]))

(def domain (environ/env :domain))

(def index
  (html
    [:h1 "Welcome!"]
    [:p "Here you can"]
    [:ul [:li "Write your data by POSTing it as JSON to /store/path."]
         [:li "Read that same data (and possibly more) by GETing /store/path."]]
    [:h2 "Example"]
    [:ol
      [:li [:p "First, we POST some data"]
           [:pre (join "\n"
                      ["$ curl --insecure \\"
                       (str "    -X POST https://" domain "/store/mypath \\")
                       "    -H \"Content-Type: application/json\" \\"
                       "    -d '{\"data\": \"mydata\"}'"])]]

      [:li [:p "Then, we GET the data"]
           [:pre (join "\n"
                       ["$ curl --insecure \\"
                        (str "    -X GET https://" domain "/store/mypath")])]]

      [:li [:p "The body of the response looks something like this"]
           [:pre (join "\n"
                       ["[{\"data\":\"mydata\","
                        "  \"time\":\"2019-07-15T11:13:49.961410Z\"}]"])]]]))
