(ns cljstut.shopping

  (:require-macros [hiccups.core :as h])
  (:require [domina :as d]
            [hiccups.runtime]
            [domina.events :as de]))

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

(defn add-help []
  (d/append! (d/by-id "shoppingForm")
             (h/html [:div.help "Click to calculate"])))

(defn remove-help []
  (d/destroy! (d/by-class "help")))

(defn ^:export init []
  (when (and js/document
             (aget js/document "getElementById"))
    (doto (d/by-id "calc")
      (de/listen! :click calculate)
      (de/listen! :mouseout remove-help)
      (de/listen! :mouseover add-help))))

;;(set! (.-onload js/window) init)

