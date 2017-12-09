(ns adventofcode-2017.utils)

(defn check-examples
  "Check function gives the expected answers for given examples

  A map m is expected as {example answer}."
  [f m]
  (= (map f (keys m)) (vals m)))
