(ns ui.core
  (:require [figwheel.client :as fw :include-macros true]
            [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(fw/watch-and-reload
  :websocket-url   "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (print "reloaded")))

(defonce state (atom 0))

(defn root-component []
  [:div
   [:p (str
         "Node version is "
         js/process.version)]
   [:p (str
         "Atom version is "
         ((js->clj
            js/process.versions)
          "atom-shell"))]
   [:h1 "Hello world!"]])

(reagent/render
  [root-component]
  (.-body js/document))
