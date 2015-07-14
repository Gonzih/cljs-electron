(defproject hello-electron "0.1.0-SNAPSHOT"
  :source-paths ["src/tools"]
  :description "A hello, world application for electron"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [figwheel "0.3.7"]
                 [reagent "0.5.0"]
                 [ring/ring-core "1.4.0"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.3.7"]]
  :cljsbuild
  {:builds
   [{:source-paths ["src/electron"],
     :id "electron-dev",
     :compiler {:output-to "resources/main.js",
                :optimizations :simple
                :pretty-print true
                :cache-analysis true}}
    {:source-paths ["src/ui"],
     :id "frontend-dev",
     :compiler {:output-dir "resources/public/js/ui-out"
                :output-to "resources/public/js/ui-core.js",
                :optimizations :none
                ; :pretty-print true
                :source-map true
                :cache-analysis true}}]}
  :figwheel {:http-server-root "public"
             :ring-handler figwheel-middleware/app
             :server-port 3449})
