(ns putoff.core
  (:require [clj-time.core])
  (:gen-class))

(def items [])

(defn help []
  (clojure.string/join "\n" [
    "Usage: putoff [command]"
    "command of of:"
    "  help      This text"
    "  ls        List all offput items"
    "  add       Add a new item"
    ""] ))

(defn- as-item
  [value]
  { :value value :time (.toString (clj-time.core/now)) })

(defn ls []
  (clojure.string/join "\n" (map #(str (get % :time) "        " (get % :value)) items)))

(defn add
  ([]
    "Usage: putoff add some-value\n")
  ([& values]
    (def items (into items (map as-item values)))
    items))

(defn putoff
  [args]
    ((get {
      "help" #(help)
      "ls" #(ls)
      "add" (fn [] (apply add (rest args)) (ls))
    } (first args) #(help))) )

(defn -main
  "Takes a string, saves it into the internets"
  [& args]
  (println (putoff args)))

