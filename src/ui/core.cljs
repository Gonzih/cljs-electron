(ns ui.core
  (:require [figwheel.client :as fw :include-macros true]
            [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(fw/watch-and-reload
  :websocket-url   "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (print "reloaded")))

(def state (atom 0))

(defn root-component []
  [:div
   [:h1 "Hello world"]
   [:button
    {:on-click #(swap! state inc)}
    (str "You clicked me "
         @state
         " times")]])

(reagent/render
  [root-component]
  (.-body js/document))
