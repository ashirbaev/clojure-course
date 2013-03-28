(ns clojure-course-task02.core
  (:import (java.io File))
  (:gen-class))


(def result (ref []))


(defn walker [matcher-func next-data-func data]
  (let [file-name (matcher-func data)
        next-data (next-data-func data)]
    (if-not (empty? next-data)
      (doall
        (pmap #(walker matcher-func next-data-func %) next-data))
      (dosync (alter result conj file-name)))))


(defn matcher [regexp]
  (fn [path]
    (if (and (not (.isDirectory path)) (.matches (.getName path) regexp))
      (.getName path)
      nil)))


(defn find-files [file-name path]
  "TODO: Implement searching for a file using his name as a regexp."
  (do
    (walker (matcher file-name) #(vec (.listFiles %)) (new File path))
    (vec (filter #(not (nil? %)) (flatten @result)))))


(defn usage []
  (println "Usage: $ run.sh file_name path"))


(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
