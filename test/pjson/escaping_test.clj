(ns
  ^{:doc "Test reading data that has characters {} [] in the string values of lazy objects do not throw exceptions"}
  pjson.escaping-test
  (:require
    [pjson.core :as pjson]
    [cheshire.core :as cheshire]
    [clojure.test :refer :all]))



(def ^String CONVERSION-MSG-OBJ "{\"mykey\":{\"mykey2\": \"abc}\"}}")
(def ^String CONVERSION-MSG-LIST "{\"mykey\":[\"mykey2\", \"abc]\"]}")



(defn test-fail-message
  "Throw an exception if the message is not correct"
  [msg]
  (cheshire/parse-string (pjson/write-str (pjson/read-str msg))))

(deftest test-conversion-lazy-obj []
         (is (not (nil? (test-fail-message CONVERSION-MSG-OBJ)))))



(deftest test-conversion-lazy-list []
         (is (not (nil? (test-fail-message CONVERSION-MSG-LIST)))))