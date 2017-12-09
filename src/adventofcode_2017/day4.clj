(ns adventofcode-2017.day4
  (:require [adventofcode-2017.utils :as utils]
            [clojure.spec.alpha :as spec]
            [clojure.string :as string]
            [orchestra.spec.test :as spec.test]))

(def passphrases (string/split-lines (slurp "resources/day4-input.txt")))

(spec/def ::word string?)
(spec/def ::passphrase (spec/coll-of ::word :distinct true))

(defn str->coll-of-strs
  [s]
  (string/split s #"\s"))

(defn valid-passphrase?
  [coll]
  (spec/valid? ::passphrase coll))

(def part-1 {"aa bb cc dd ee" true
             "aa bb cc dd aa" false
             "aa bb cc dd aaa" true})

(utils/check-examples (comp valid-passphrase? str->coll-of-strs) part-1)
;; => true

(frequencies (map (comp valid-passphrase? str->coll-of-strs) passphrases))
;; => {false 175, true 337}

(defn sort-letters
  "Sort the letters of a word alphabetically"
  [s]
  (apply str (sort s)))

(def part-2 {"abcde fghij" true
             "abcde xyz ecdab" false
             "a ab abc abd abf abj" true
             "iiii oiii ooii oooi oooo" true
             "oiii ioii iioi iiio" false})

(utils/check-examples (comp valid-passphrase? #(map sort-letters %) str->coll-of-strs)
                      part-2)
;; => true

(frequencies (map (comp valid-passphrase? #(map sort-letters %) str->coll-of-strs) passphrases))
;; => {false 281, true 231}
