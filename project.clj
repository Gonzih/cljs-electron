(defproject hello-atom-shell "0.1.0-SNAPSHOT"
  :source-paths ["src/tools"]
  :description "A hello, world application for atom-shell"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [figwheel "0.1.5-SNAPSHOT"]
                 ; [ring/ring-core "1.3.1"]
                 ]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.1.5-SNAPSHOT"]]
  :cljsbuild
  {:builds
   [{:source-paths ["src/atom"],
     :id "atom-dev",
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
                ; :source-map true
                :cache-analysis true}}]}
  :figwheel {:http-server-root "public"
             ; :ring-handler src.tools.figwheel-ring-handler/app
             :server-port 3449})
