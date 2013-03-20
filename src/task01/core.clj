(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-href [tag]
  (map #(:href (second %))
    (filter #(= (first %) :a) (rest(rest tag)))))


(defn get-links []
  (let [data (parse "clojure_google.html")]
    (loop [result [], tags (list data)]
      (if (empty? tags)
        result
        (let [tag (first tags)]
          (recur (if (= (second tag) {:class "r"})
                   (concat result (get-href tag))
                   result)
            (concat (next tags) (filter #(keyword? (first %)) (rest(rest tag))))))))))


(defn -main []
  (let [result (get-links)]
    (do
;      (println result)
      (println (str "Found " (count result) " links!")))))


