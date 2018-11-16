(ns cecab.axel-f.sample
  (:require [axel-f.core :as axel-f]))

(defn run-formula
  "Run the formula on a context of values pairing l-vars
   with l-vals"
  [formula l-vars l-values]

  (axel-f/run formula
    (reduce
     merge
     {}
     (map (fn [k v] {k v}) l-vars l-values))))
  
(defn get-cells-refs
  "Wrapper for axel-f/compile to extract all cell references"
  [str-formula]
    (->>
     (axel-f/compile str-formula)
     flatten
     (partition 2)
     (filter #(= :OBJREF (first %)))
     (mapv last)))


