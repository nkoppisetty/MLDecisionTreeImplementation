import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Heuristics{

    public String getBestAttribute(DataSet dataSet, ArrayList<String> attributeListOriginal, int method) {
        double pTotal = dataSet.getpTotal();
        double nTotal = dataSet.getnTotal();
        ArrayList<String> attributeList = dataSet.getAttributeList();

        double gain;
        double maxGain = -1.0;
        String bestAttr = null;

        Map<String,ArrayList<String>> map = getDataMap(dataSet, attributeListOriginal);
        ArrayList<String> results = map.get("Class");

        double pT, nT, pp, nn, pn, np;
        for (int i = 0; i<attributeList.size()-1;i++){
            pT = 0; nT=0; pp = 0; nn = 0; pn =0; np=0;
            ArrayList<String> values = map.get(attributeList.get(i));

            for(int j = 0; j < values.size(); j++){
                if(values.get(j).equals("0")){
                    if(results.get(j).equals("0")){
                        nn++;
                    }else{
                        np++;
                    }
                }else{
                    if(results.get(j).equals("0")){
                        pn++;
                    }else{
                        pp++;
                    }
                }
            }
            pT = pn+pp;
            nT = nn+np;

            if(method == 1){  // info gain
                double total = nT+pT;
                gain = getEntropy(pTotal, nTotal) - (nT/total)*getEntropy(np,nn) - (pT/total)*getEntropy(pp,pn);
            }else{  // variance impurity
                double total = pTotal+nTotal;
                gain = getVarianceImpurity(pTotal, nTotal) - (nT/total)*getVarianceImpurity(np,nn) - (pT/total)*getVarianceImpurity(pp,pn);
            }

            if(Double.compare(maxGain, gain) < 0){
                maxGain = gain;
                bestAttr = attributeList.get(i);
            }
        }

        return bestAttr;
    }

    public double getEntropy(double p, double n){
        if(p == 0 || n == 0){
            return 0;
        }else if(p == n){
            return 1;
        }else {
            double total = p + n;
            double pProb = p/total;
            double nProb = n/total;
            return  -(pProb*log(pProb)+nProb*log(nProb));
        }
    }

    public double log(double num){
        return Math.log(num)/Math.log(2);
    }


    public double getVarianceImpurity(double p, double n){
        if(p == 0 || n == 0){
            return 0;
        }else {
            double total = p + n;
            return  (p*n)/(total*total);
        }
    }

    public Map<String,ArrayList<String>> getDataMap(DataSet dataSet, ArrayList<String> attributeList){
        ArrayList<ArrayList<String>> data = dataSet.getData();

        Map<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();

        for(int i=0;i<attributeList.size();i++){
            for(int j=0;j<data.size();j++){
                if (map.containsKey(attributeList.get(i))){
                    map.get(attributeList.get(i)).add(data.get(j).get(i));
                }
                else{
                    ArrayList<String> values = new ArrayList<String>();
                    values.add(data.get(j).get(i));
                    map.put(attributeList.get(i), values);
                }
            }
        }

        return map;
    }

    public Map<String,ArrayList<ArrayList<String>>> getnextDataSetsMap(ArrayList<ArrayList<String>> data, int index){
        Map<String, ArrayList<ArrayList<String>>> nextDataSetsMap = new HashMap<String, ArrayList<ArrayList<String>>>();

        for(int i=0;i<data.size();i++){
            if(data.get(i).get(index).equals("0")){
                if(nextDataSetsMap.containsKey("0")){
                    nextDataSetsMap.get("0").add(data.get(i));
                }
                else{
                    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
                    list.add(data.get(i));
                    nextDataSetsMap.put("0",list);
                }
            }
            else{
                if(nextDataSetsMap.containsKey("1")){
                    nextDataSetsMap.get("1").add(data.get(i));
                }
                else{
                    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
                    list.add(data.get(i));
                    nextDataSetsMap.put("1",list);
                }
            }
        }
        return nextDataSetsMap;
    }

}
