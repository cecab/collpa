(ns cecab.axel-f.http
  (:require [org.httpkit.server :as h]
            [cognitect.transit :as transit]
	    [clojure.tools.nrepl :as nrepl))
(import [java.io ByteArrayInputStream ByteArrayOutputStream])


(defn respond
  "Response function, will resolve the symbol of the function
   and invoke it with the args received. It expects a Transit
   encode value"
  [req]
  
  (let [           
        out (ByteArrayOutputStream. 4096)
        writer (transit/writer out :json)
        edn-value
        (transit/read (transit/reader
                       (:body req)
                       :json))
        fn-call (first edn-value)
        rest-args
        (map
         (fn [a] (if (string? a) (clojure.string/trim a) a))
         (rest (rest edn-value)))]
    (->> (try (apply (resolve fn-call)
                     rest-args)
              (catch Exception ex
                (str "Exception:"
                     ex)))
         (transit/write writer))
    (.toString out)))

(defn http-process 
  "A function to start the httpserver"
  [req]
  {:status 200
   :headers
   {"content-type" "application/edn;charset=UTF-8"}
   :body
   (try
     (respond req)
     (catch Exception ex
       (.getMessage ex)))})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server []
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (reset! server
          (h/run-server #'http-process
                        {:port 9200
                         :thread 8})))

(defn -main []
  (start-server)
  (nrepl/start-server :port 6666 :bind "0.0.0.0"))
