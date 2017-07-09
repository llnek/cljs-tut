(defproject cljstut "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ;; CLJ AND CLJS source code paths
  :source-paths ["src/main/clojure" "src/main/cljsc" "src/main/brepl"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [domina "1.0.3"]
                 [hiccups "0.3.0"]
                 [org.clojure/clojurescript "1.9.671"]]

  ;; lein-cljsbuild plugin to build a CLJS project
  :plugins [[lein-cljsbuild "1.1.6"]
            [compojure "1.6.0"]
            [lein-ring "0.12.0"]]

  :ring {:handler cljstut.core/rhandler}

  ;; cljsbuild options configuration
  :cljsbuild
  {:builds
   {:dev
    {;; clojurescript source code path
     :source-paths ["src/main/brepl" "src/main/cljsc"]
     ;; Google Closure Compiler options
     :compiler {;; the name of emitted JS script file
                :output-to "resources/public/js/main_dbg.js"
                ;; minimum optimization
                :optimizations :whitespace
                ;; prettyfying emitted JS
                :pretty-print true}}
    :pre-prod
    {;; clojurescript source code path
     :source-paths ["src/main/brepl" "src/main/cljsc"]
     :compiler {;; different JS output name
                :output-to "resources/public/js/main_pre.js"
                ;; simple optimization
                :optimizations :simple
               ;; no need prettyfication
               :pretty-print false}}
    :prod
    {;; clojurescript source code path
     :source-paths ["src/main/cljsc"] ;; no "src/brepl"
     :compiler {;; different JS output name
                :output-to "resources/public/js/main.js"
                ;; advanced optimization
                :optimizations :advanced
                ;; no need prettyfication
                :pretty-print false}}}}
  ;; to clean JS files generated during the build
  :clean-targets ^{:protect false} [:target-path "resources/public/js/"])








