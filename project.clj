(defproject paste-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-defaults "0.3.2"]
                 [jumblerg/ring-cors "2.0.0"]
                 [environ "1.1.0"]
                 [hiccup "1.0.5"]
                 [bananaoomarang/ring-debug-logging "1.1.0"]
                 [clojure.java-time "0.3.2"]
                 [ring "1.7.1"]
                 [cheshire "5.8.1"]
                 [clj-http "3.10.0"]
                 [compojure "1.6.1"]]

  :plugins [[cider/cider-nrepl "0.22.0-beta8"]
            [lein-environ "1.1.0"]]

  :repl {:plugins [[cider/cider-nrepl "0.22.0-beta8"]]}
  :repl-options {:init-ns paste-server.core}

  :profiles {:dev {:main paste-server.core/-dev-main
                   :env {:port "3000"
                         :ssl-port "3443"
                         :keystore "cert/keystore.jks"
                         :password "password"
                         :domain "localhost:3443"}}}

  :main paste-server.core/-main)

