(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-href [tag]
  (->> (nnext tag)
       (filter #(= (first %) :a))
       (map #(:href (second %)))))


(defn get-links []
  (let [data (parse "clojure_google.html")]
    (loop [result [], tags (list data)]
      (if (empty? tags)
        result
        (let [[tag & rst] tags]
          (recur (if (= (second tag) {:class "r"})
                   (concat result (get-href tag))
                   result)
            (concat rst (filter #(keyword? (first %)) (nnext tag)))))))))


(defn -main []
  (let [result (get-links)]
    (do
;      (println result)
      (println (str "Found " (count result) " links!")))))



