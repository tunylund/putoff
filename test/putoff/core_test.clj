(ns putoff.core-test
  (:require [clojure.test :refer :all]
            [putoff.core :refer :all]))

(deftest help-test
  (testing "should display help"
    (is (clojure.string/includes? (help) "Usage:" ))))

(deftest add-ls-test 
  (testing "should display add help if missing value"
    (is (clojure.string/includes? (add) "Usage:" )))

  (testing "should add and display list of things"
    (is (= (get (last (add "c")) :value) "c"))))

(deftest putoff-test 
  (testing "should display help"
    (is (clojure.string/includes? (putoff ["help"]) "Usage:")))
  (testing "should display help for add"
    (is (clojure.string/includes? (putoff ["add"]) "Usage:")))
  (testing "should add an item and list items"
    (is (clojure.string/includes? (putoff ["add" "example-value"]) "example-value"))
    (is (clojure.string/includes? (putoff ["ls"]) "example-value")))
  )

