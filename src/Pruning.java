import java.util.Random;

public class Pruning {

    public DecisionTree prune(DecisionTree D, int L, int K, DataSet dataSet) {
        Random random = new Random();
        DecisionTree DBest = D.deepCopy();
        double bestAccuracy = DBest.getAccuracy(dataSet);
        for (int i = 1; i <= L; i++) {
            DecisionTree DDash = D.deepCopy();
            int M = 1 + random.nextInt(K);
            for(int j=1; j <= M; j++){
                int N = DDash.getNonLeafNodes();
                if(N>1){
                    int P = 1 + random.nextInt(N);
                    DDash.replaceNode(P);
                }else{
                    break;
                }
            }

            double accuracy = DDash.getAccuracy(dataSet);
            if (Double.compare(bestAccuracy, accuracy) < 0){
                bestAccuracy = accuracy;
                DBest = DDash.deepCopy();
            }
        }
        return DBest;
    }

}
