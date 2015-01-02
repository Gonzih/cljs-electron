(ns ui.core
  (:require [figwheel.client :as fw :include-macros true]))

(enable-console-print!)

(js/alert "Hello from cljs")

(fw/watch-and-reload
  :websocket-url   "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (print "reloaded")))
