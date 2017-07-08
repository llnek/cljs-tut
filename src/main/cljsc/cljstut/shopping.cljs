(ns cljstut.shopping

  (:require [domina :refer [set-value! by-id value] :as d]))

(defn calculate []
  (let [quantity (d/value (d/by-id "quantity"))
        price (d/value (d/by-id "price"))
        tax (d/value (d/by-id "tax"))
        discount (d/value (d/by-id "discount"))]
    (d/set-value! (d/by-id "total") (-> (* quantity price)
                                    (* (+ 1 (/ tax 100)))
                                    (- discount)
                                    (.toFixed 2)))
    false))

(defn init []
  (if (and js/document
           (.-getElementById js/document))
    (let [the-form (d/by-id "shoppingForm")]
      (set! (.-onsubmit the-form) calculate))))

(set! (.-onload js/window) init)

