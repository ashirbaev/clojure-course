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


(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
  (let [data (parse "clojure_google.html")]
    nil))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


