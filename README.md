# Decision tree learning algorithm





# Instructions:

change the directory to the src folder
```
cd MLDecisionTreeImplementation/src
```
compile Main.java 
```
javac Main.java
```
run 
```
java Main <L> <K> <training-set> <validation-set> <test-set> <to-print>
```
example:
```
java Main 4 5 "../data_sets1/training_set.csv" "../data_sets1/validation_set.csv" "../data_sets1/test_set.csv" "no"
```

Arguments Info:
L: random integer (used in the post-pruning algorithm)
K: random integer (used in the post-pruning algorithm)
training-set:  "../data_sets1/training_set.csv" or "../data_sets2/training_set.csv"
validation-set: "../data_sets1/validation_set.csv" or "../data_sets2/validation_set.csv"
test-set: "../data_sets1/test_set.csv" or "../data_sets2/test_set.csv"
to-print: "yes" or "no
