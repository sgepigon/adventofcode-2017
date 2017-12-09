(ns adventofcode-2017.day2
  (:require [adventofcode-2017.utils :as utils]
            [clojure.math.combinatorics :as combo]
            [clojure.string :as string]))

(defn- str->spreadsheet
  [s]
  (->> (string/split s #"\n")
       (map #(string/split % #"\t"))
       (map (fn [coll] (map (fn [s] (Integer/parseInt s)) coll)))))

(def spreadsheet (str->spreadsheet (slurp "resources/day2-input.txt"))) ; 16x16

(defn min-max-diff
  "Return the difference between the largest value and the smallest value."
  [row]
  (- (apply max row) (apply min row)))

(defn evenly-divisible-quotient
  "Return the quotient of the only two numbers in each row where one evenly
  divides the other."
  [row]
  (->> (combo/combinations row 2)
       (map (fn [pair] (/ (apply max pair) (apply min pair))))
       (filter int?)
       first))

(defn checksum
  "Return the sum of all the differences between the largest and smallest value in
  a row."
  [f spreadsheet]
  (apply + (map f spreadsheet)))

(def part-1 {[[5 1 9 5]                 ; 8 + 4 + 6 = 18
              [7 5 3]
              [2 4 6 8]] 18})

(def part-2 {[[5 9 2 8]                  ; 4 + 3 + 2 = 9
              [9 4 7 3]
              [3 8 6 5]] 9})


(utils/check-examples #(checksum min-max-diff %) part-1)
;; => true

(utils/check-examples #(checksum evenly-divisible-quotient %) part-2)
;; => true

(checksum min-max-diff spreadsheet)
;; => 44887

(checksum evenly-divisible-quotient spreadsheet)
;; => 242
