(ns adventofcode-2017.day1
  (:require [adventofcode-2017.utils :as utils]
            [clojure.string :as string]))

(def captcha (string/trim-newline (slurp "resources/day1-input.txt")))

(defn pairs
  "Return a seq of pairs of a sequence.

  The sequence is circular, so the digit after the last digit is the first digit
  in the list."
  [coll]
  (partition 2 1 [(first coll)] coll))

(defn halfway-pairs
  "Return a seq of pairs halway around the sequence.

  The sequence is circular, so the digit after the last digit is the first digit
  in the list."
  [captcha]
  (let [halfway #(/ (count %) 2)
        [left right] (split-at (halfway captcha) captcha)]
    (map #(list %1 %2) left right)))


(defn solve
  "Find the sum of all digits that match the next digit, given by f, in the list."
  [f captcha]
  (->> (seq captcha)
       f
       (filter (fn [[left right]] (= left right)))
       (map first)
       (map #(Character/digit % 10))
       (apply +)))

(def part-1 {"1122" 3
             "1111" 4
             "1234" 0
             "91212129" 9})

(utils/check-examples #(solve pairs %) part-1)
;; => true

(def part-2 {"1212" 6
             "1221" 0
             "123425" 4
             "123123" 12
             "12131415" 4})

; (* 2) because the pairs are reflected
(utils/check-examples #(* (solve halfway-pairs %) 2) part-2)
;; => true

(map #(% captcha) [#(solve pairs %)
                   #(* (solve halfway-pairs %) 2)])
;; => (1136 1092)
