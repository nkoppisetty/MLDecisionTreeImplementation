# Decision tree learning algorithm

Implemented a decision tree learning algorithm, using two heuristics for selecting the next best attribute.

1. Information gain heuristic 
2. Variance impurity heuristic

Data Sets:
Each dataset is divided into three sets: the training set, the validation set and the test set. The first line in the file gives the attribute names. Each line after that is a list of attribute values separated by a comma. The last attribute is the class-variable.

Implemented a post pruning algorithm on the two decision trees using validation set. 

# How to run:

Step 1: change the directory to the src folder
```
cd MLDecisionTreeImplementation/src
```
Step 2: compile Main.java file
```
javac Main.java
```
Step 3: run Main.java using below parameters as command line arguments<br />

L: random integer (used in the post-pruning algorithm)<br />
K: random integer (used in the post-pruning algorithm)<br /> 
training-set:  "../data_sets1/training_set.csv" or "../data_sets2/training_set.csv"<br />
validation-set: "../data_sets1/validation_set.csv" or "../data_sets2/validation_set.csv"<br />
test-set: "../data_sets1/test_set.csv" or "../data_sets2/test_set.csv"<br />
to-print: "yes" or "no<br />

```
java Main <L> <K> <training-set> <validation-set> <test-set> <to-print>
```
Example:
```
java Main 4 5 "../data_sets1/training_set.csv" "../data_sets1/validation_set.csv" "../data_sets1/test_set.csv" "no"
```
