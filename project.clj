(defproject rcmplayer "0.1.0-SNAPSHOT"
  :description "Remote Control for MPlayer"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [me.raynes/conch "0.8.0"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [compojure "1.2.1"]
                 [hiccup "1.0.5"]]
  :main rcmplayer.core
  :aot [rcmplayer.core])
