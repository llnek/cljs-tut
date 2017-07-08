(ns cljstut.login)

(defn- getid "" [id]
  (.getElementById js/document id))

(defn- getv "" [obj] (.-value obj))

;; define the function to be attached to form submission event
(defn validateForm []
  ;; get email and password element from their ids in the HTML form
  (let [email (getid "email")
        password (getid "password")]
    (if (and (> (count (getv email)) 0)
             (> (count (getv password)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

;; define the function to attach validate-form to onsubmit event of
;; the form
(defn init []
  ;; verify that js/document exists and that it has a getElementById
  ;; property
  (if (and js/document
           (.-getElementById js/document))
    ;; get loginForm by element id and set its onsubmit property to
    ;; our validate-form function
    (let [login-form (getid "loginForm")]
      (set! (.-onsubmit login-form) validateForm))))

;; initialize the HTML page in unobtrusive way
(set! (.-onload js/window) init)


