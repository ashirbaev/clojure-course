(ns clojure-course-task02.core
  (:import (java.io File))
  (:gen-class))


(def result (ref []))


(defn walker [matcher-func next-data-func data]
  (let [result (matcher-func data)
        next-data (next-data-func data)]
    (if-not (empty? next-data)
      (conj result
            (doall
              (map #(walker matcher-func next-data-func %) next-data)))
      result)))


(defn matcher [regexp]
  (fn [path]
    (if (and (not (.isDirectory path)) (.matches (.getName path) regexp))
      (.getName path)
      nil)))


(defn find-files [file-name path]
  "TODO: Implement searching for a file using his name as a regexp."
  (vec (filter #(not (nil? %))
       (flatten (walker (matcher file-name) #(vec (.listFiles %)) (new File path))))))


(defn usage []
  (println "Usage: $ run.sh file_name path"))


(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
