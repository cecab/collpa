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

(defn is-object?
  "Validate that x is like [:OBJREF ..]"
  [x]
  (and
   (vector? x)
   (= :OBJREF (first x))))

(defn pickup-objs*
  [acc v]
    (cond
      (is-object? v)
      (conj acc v)
      (vector? v)
      (reduce pickup-objs* acc v)
      :else
      acc))

  
(defn get-cells-refs
  "Wrapper for axel-f/compile to extract all cell references"
  [str-formula]
  (->>
   (pickup-objs* [] (axel-f/compile str-formula))
   (mapv last)))
(defn doble [x]
  (* 2 x))

(comment
  ;; ---
  (def str-formula "=concat(A2,A3)")
  (clojure.string/upper-case formula)
  (axel-f/compile "=CONCAT(A2,A3)")
  (axel-f/compile "=IF(C9 = 10,\"TEN\", \"NO TEN\")")
  (axel-f/run
    "=IF(C9 = 10,\"TEN\", \"NO TEN\")"
    {:C9 100})
  (def p-f
    [:FNCALL "IF" [[:EQ_EXPR [:OBJREF "C9"] 10] [:STRING "TEN"] [:STRING "NO TEN"]]])
  (flatten
   [:FNCALL "IF" [[:EQ_EXPR [:OBJREF "C9"] 10] [:STRING "TEN"] [:STRING "NO TEN"]]])
  ;;---
  
  (->>
   (pickup-objs [] (axel-f/compile "=IF(C9 = 10,\"TEN\", C5)"))
   (map last))
  ("C9" "C5")
  
  (get-cells-refs "=AVERAGE(A17:B21)")
  ["=AVERAGE(A17:B21)"
   ["A17:B21"]
   [[[76 64] [6.5 23] [65.5 5] [54 5.23] [13.6 23.23]]]]
  ;; ---
  (run-formula
   "=AVERAGE(A17:B21)"
   ["A17:B21"]
   [[[76 64] [6.5 23] [65.5 5] [54 5.23] [13.6 23.23]]])
  33.60600000000001
    
  )
