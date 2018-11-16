(ns cecab.axel-f.sample
  (:require [axel-f.core :as axel-f]))
(comment
  ;;---
  ;; From https://github.com/xapix-io/axel-f
  (axel-f/run "=CONCAT(A1,A2)", {:A1 "algo" :A2 "pasa"})
  (axel-f/compile "=CONCAT(A1,\"PERU\")")
  [:FNCALL "CONCAT" [[:OBJREF "A1"] [:STRING "PERU"]]]
  )
