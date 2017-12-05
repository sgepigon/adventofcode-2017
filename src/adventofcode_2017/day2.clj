(ns adventofcode-2017.day2
  (:require [clojure.string :as string]))

(defn- str->spreadsheet
  [s]
  (->> (string/split s #"\n")
       (map #(string/split % #"\t"))
       (map (fn [coll] (map (fn [s] (Integer/parseInt s)) coll)))))


(def spreadsheet (str->spreadsheet (slurp "resources/day2-input.txt"))) ; 16x16

(def part-1 {[[5 1 9 5]                 ; 8 + 4 + 6 = 18
              [7 5 3]
              [2 4 6 8]] 18})

(defn min-max-diff
  "Return the difference between the largest value and the smallest value."
  [row]
  (- (apply max row) (apply min row)))

(defn checksum
  "Return the sum of all the differences between the largest and smallest value in
  a row."
  [spreadsheet]
  (apply + (map min-max-diff spreadsheet)))

(= (map checksum (keys part-1))
   (vals part-1))

(checksum spreadsheet)
;; => 44887
