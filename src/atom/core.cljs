(ns atom.core)

(def app            (js/require "app"))
(def browser-window (js/require "browser-window"))
(def crash-reporter (js/require "crash-reporter"))

(def main-window (atom nil))

(defn init-browser []
  (reset! main-window (browser-window.
                        (clj->js {:width 800
                                  :height 600})))
  (.loadUrl @main-window (str "file://" js/__dirname "/resources/public/index.html"))
  (.on @main-window "closed" #(reset! main-window nil)))

(.start crash-reporter)
(.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                (.quit app)))
(.on app "ready" init-browser)
