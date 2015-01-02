(ns ui.core
  (:require [figwheel.client :as fw :include-macros true]
            [reagent.core :as reagent :refer [atom]]
            [clojure.string :as string]))

(enable-console-print!)

(fw/watch-and-reload
  :websocket-url   "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn [] (print "reloaded")))

(defonce state (atom 0))
(defonce shell-result (atom ""))
(defonce command (atom ""))

(defonce proc (js/require "child_process"))

(defn run-process []
  (let [[cmd & args] (string/split @command #"\s")
        p (.spawn proc cmd (clj->js args))]
    (.on (.-stdout p)
         "data"
         #(swap! shell-result (fn [v] (str % v)))))
  (reset! command ""))

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
   [:h1 "Hello world!"]
   [:button
    {:on-click #(swap! state inc)}
    (str "You clicked me "
         @state
         " times")]
   [:p
    [:form
     {:on-submit (fn [e]
                   (run-process)
                   (.preventDefault e))}
     [:input#command
      {:type :text
       :on-change (fn [e]
                    (reset! command
                            (.-value (.-target e))))
       :value @command
       :placeholder "type in shell command"}]]]
   [:pre @shell-result]])

(reagent/render
  [root-component]
  (.-body js/document))
