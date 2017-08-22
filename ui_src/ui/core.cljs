(ns ui.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as string :refer [split-lines]]))

(def join-lines (partial string/join "\n"))

(enable-console-print!)

(defonce state        (atom 0))
(defonce shell-result (atom ""))
(defonce command      (atom ""))

(defonce proc (js/require "child_process"))

(defn append-to-out [out]
  (swap! shell-result str out))

(defn run-process []
  (when-not (empty? @command)
    (println "Running command" @command)
    (let [[cmd & args] (string/split @command #"\s")
          js-args (clj->js (or args []))
          p (.spawn proc cmd js-args)]
      (.on p "error" (comp append-to-out
                           #(str % "\n")))
      (.on (.-stderr p) "data" append-to-out)
      (.on (.-stdout p) "data" append-to-out))
    (reset! command "")))

(defn root-component []
  [:div
   [:div.logos
    [:img.electron {:src "img/electron-logo.png"}]
    [:img.cljs {:src "img/cljs-logo.svg"}]
    [:img.reagent {:src "img/reagent-logo.png"}]]
   [:pre "Versions:"
    [:p (str "Node     " js/process.version)]
    [:p (str "Electron " ((js->clj js/process.versions) "electron"))]
    [:p (str "Chromium " ((js->clj js/process.versions) "chrome"))]]
   [:button
    {:on-click #(swap! state inc)}
    (str "Clicked " @state " times")]
   [:p
    [:form
     {:on-submit (fn [^js/Event e]
                   (.preventDefault e)
                   (run-process))}
     [:input#command
      {:type :text
       :on-change (fn [^js/Event e]
                    (reset! command
                            ^js/String (.-value (.-target e))))
       :value @command
       :placeholder "type in shell command"}]]]
   [:pre (join-lines (take 100 (reverse (split-lines @shell-result))))]])

(reagent/render
  [root-component]
  (js/document.getElementById "app-container"))
