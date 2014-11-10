(ns rcmplayer.core
  "Core functionality for RCMPlayer"
  (:require [clojure.tools.cli :refer [parse-opts]]
            [ring.util.response :refer [redirect]]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources]]
            [hiccup.page :refer [html5 include-css]]
            [rcmplayer.mplayer :as mplayer])
  (:gen-class :main true))

(defn button-to
  [path label]
  [:form {:method :post :action path}
   [:div.form-group
    [:button.btn.btn-primary.form-control {:type :submit} label]]] )

(defn index []
  (html5
    [:head  [:title "RCMPlayer - Remote Control for MPlayer"]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"}]
     (include-css "/css/bootstrap.min.css")]
    [:body
     [:div.container-fluid
      ; (button-to "/play" "Play")
      (button-to "/pause" "Pause")
      (button-to "/rewind" "<")
      (button-to "/forward" ">")
      (button-to "/mute" "Mute")
      (button-to "/quit" "Close")]]))

; (defn play []
;   (mplayer/play)
;   (redirect "/"))

(defn pause []
  (mplayer/pause)
  (redirect "/"))

(defn rewind []
  (mplayer/rewind)
  (redirect "/"))

(defn forward []
  (mplayer/forward)
  (redirect "/"))

(defn mute []
  (mplayer/mute)
  (redirect "/"))

(defn quit []
  (mplayer/quit)
  (redirect "/"))

(defroutes app
  (GET "/" [] (index))
  ; (POST "/play" [] (play))
  (POST "/pause" [] (pause))
  (POST "/rewind" [] (rewind))
  (POST "/forward" [] (forward))
  (POST "/mute" [] (mute))
  (POST "/quit" [] (quit))
  (resources "/")
  )

(def cli-opts
  [["-p" "--port PORT" "Port which HTTP server should listen to."
    :parse-fn #(Integer/parseInt %)
    :default 5000]
   ["-m" "--mplayer PATH" "Path to MPlayer."
    :default "/usr/bin/mplayer"]])

(defn -main
  [& args]
  (let [{:keys [options arguments] :as cli} (parse-opts args cli-opts)]
    (mplayer/play options arguments)
    (let [port (:port options)]
      (jetty/run-jetty (site #'app) {:port port :join? false}))))
