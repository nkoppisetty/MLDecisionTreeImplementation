Language used: Java
Version: Java 9.0.1

Instructions:
1. cd Assignment1/src
2. javac Main.java
3. java Main <L> <K> <training-set> <validation-set> <test-set> <to-print>
Sample:
java Main 4 5 "../data_sets1/training_set.csv" "../data_sets1/validation_set.csv" "../data_sets1/test_set.csv" "no"

Arguments Info:
L: random integer (used in the post-pruning algorithm)
K: random integer (used in the post-pruning algorithm)
training-set:  "../data_sets1/training_set.csv" or "../data_sets2/training_set.csv"
validation-set: "../data_sets1/validation_set.csv" or "../data_sets2/validation_set.csv"
test-set: "../data_sets1/test_set.csv" or "../data_sets2/test_set.csv"
to-print: "yes" or "no"



Creates a output.txt file with following:
Data Set 1 Output:
Before Pruning Accuracies for both heuristics on test data
After Pruning Accuracies for 10 pairs of LK for both heuristics on test data
Data Set 2 Output:
Before Pruning Accuracies for both heuristics on test data
After Pruning Accuracies for 10 pairs of LK for both heuristics on test data