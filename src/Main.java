public class Main {

    public static void main(String[] args) {
        try {
            int L = Integer.parseInt(args[0]);
            int K = Integer.parseInt(args[1]);
            String fileName_trainingSet = args[2];
            String fileName_validationSet = args[3];
            String fileName_testSet = args[4];
            String print = args[5];

            DataSet training_set = new DataSet(fileName_trainingSet);
            DataSet validation_set = new DataSet(fileName_validationSet);
            DataSet test_set = new DataSet(fileName_testSet);

            DecisionTree infoGainTree = new DecisionTree();
            infoGainTree.buildInfoGainHeuristic(training_set);

            DecisionTree VITree = new DecisionTree();
            VITree.buildVarianceImpurityHeuristic(training_set);

            Pruning pruning = new Pruning();

            DecisionTree infoGainTreePruned = pruning.prune(infoGainTree, L, K, validation_set);
            DecisionTree VITreePruned = pruning.prune(VITree, L, K, validation_set);

            System.out.println("Before Pruning:");
            System.out.println("Accuracy using 1st Heuristic on test_test: "+infoGainTree.getAccuracy(test_set));
            System.out.println("Accuracy using 2nd Heuristic on test_test: "+VITree.getAccuracy(test_set));

            System.out.println("After Pruning: (L = "+L+" K = "+ K+")");
            System.out.println("Accuracy using 1st Heuristic on test_test: "+infoGainTreePruned.getAccuracy(test_set));
            System.out.println("Accuracy using 2nd Heuristic on test_test: "+VITreePruned.getAccuracy(test_set));


            if(print.toLowerCase().equals("yes")){
                System.out.println("\n\n------------------Tree using 1st Heuristic before pruning------------------");
                infoGainTree.print();
                System.out.println("\n\n------------------Tree using 2nd Heuristic before pruning------------------");
                VITree.print();
                System.out.println("\n\n------------------Tree using 1st Heuristic after pruning------------------");
                infoGainTreePruned.print();
                System.out.println("\n\n------------------Tree using 2nd Heuristic after pruning------------------");
                VITreePruned.print();
            }


        }catch (Exception e){
            System.out.println("Error:"+e);
        }
    }

}
