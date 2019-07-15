(ns paste-server.core
  (:require ;; misc
            [java-time :as time]
            [cheshire.core :as json]
            [environ.core :as environ]

            ;; compojure
            [compojure.route]
            [compojure.core :refer [defroutes GET POST]]

            ;; ring
            [ring.adapter.jetty :as jetty]
            [ring.util.request :as request]
            [ring.util.response :as response]

            ;; ring middleware
            [ring.middleware.defaults :refer :all]
            [jumblerg.middleware.cors :refer [wrap-cors]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring-debug-logging.core :refer [wrap-with-logger]]

            ;; project
            [paste-server.views :as views]
            [paste-server.store :as store]))

;; ++++++++++
;; + Routes +
;; ++++++++++

(defn store-GET
  "Handles GET-request to /store/:where."
  [where]
  (json/generate-string
    (store/store-read where)))

(defn parse-json-body [req]
  (json/parse-string
   (request/body-string req)
   true))

(defn store-POST
  "Handles POST-request to /store/:where."
  [where req]
  (let [body (parse-json-body req)
        data (str (:data body ""))
        debug? (:debug body)]

    (do (store/store-write
          where
          {:data data
           :time (str (time/instant)) })

        (if-not debug?
          ""
          (json/generate-string
            {:where (str where)
             :data data})))))

(defroutes routes
  (GET "/" req
    (-> (response/response views/index)
        (response/content-type "text/html")))

  (GET "/store/:where" [where :as req]
    (-> (response/response (store-GET where))
        (response/content-type "application/json")))

  (POST "/store/:where" [where :as req]
    (-> (response/response (store-POST where req))
        (response/content-type "application/json")))

  (compojure.route/not-found
   (response/response "")))

;; +++++++
;; + App +
;; +++++++

(def app
  (-> #'routes
      (wrap-cors routes ".*")
      (wrap-defaults secure-api-defaults)))

(def options
  {:ssl? true
   :port (Integer. (environ/env :port))
   :ssl-port (Integer. (environ/env :ssl-port))
   :keystore (environ/env :keystore)
   :key-password (environ/env :password)})

(defn -dev-main []
  (jetty/run-jetty
    (-> #'app
        wrap-reload
        wrap-with-logger)

    options))

(defn -main [] (jetty/run-jetty app options))
