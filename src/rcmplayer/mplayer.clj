(ns rcmplayer.mplayer
  (:require [me.raynes.conch.low-level :as sh]
            [clojure.string :refer [join]]))

(def ^:private mplayer-process
  "MPlayer OS process."
  (atom nil))

(def control-opts
  "MPlayer options for control mode."
  ["-slave" "-quiet"])

(defn play
  [{:keys [mplayer]} mplayer-args]
  (let [p (apply sh/proc mplayer (into control-opts mplayer-args))]
    (reset! mplayer-process p)))

(defn feed-to
  "Helper to feed cmd to process p."
  [p cmd]
  (sh/feed-from-string p (str cmd (System/lineSeparator))))

(defn pause
  []
  (feed-to @mplayer-process "pause"))

(defn rewind
  []
  (feed-to @mplayer-process "seek -10 0"))

(defn forward
  []
  (feed-to @mplayer-process "seek +10 0"))

(defn mute
  []
  (feed-to @mplayer-process "mute"))

(defn quit
  []
  (feed-to @mplayer-process "quit"))
