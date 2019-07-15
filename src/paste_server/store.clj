(ns paste-server.store)

(def ^:private store (atom {}))

(defn store-write [key val]
  (swap! store #(merge-with concat %1 {key [val]})))

(defn store-read [key] (get (deref store) key))
