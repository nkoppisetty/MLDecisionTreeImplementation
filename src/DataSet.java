import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DataSet{
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> attributeList;
    private int pTotal;
    private int nTotal;
    private int size;

    //initializing dataset from lists
    public DataSet(ArrayList<ArrayList<String>> data, ArrayList<String> attributeList){
        this.data = data;
        this.attributeList = attributeList;
        pTotal = 0;
        nTotal = 0;

        for(int i=0; i < data.size();i++){
            if(data.get(i).get(data.get(i).size()-1).equalsIgnoreCase("1")){
                pTotal++;
            }
            else{
                nTotal++;
            }
        }
    }

    //initializing dataset from file
    public DataSet(String fileName) throws Exception{
        readDataSet(fileName);
    }

    private void readDataSet(String fileName) {
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            attributeList = new ArrayList<String>(Arrays.asList(line.split(",")));
            pTotal = 0;
            nTotal = 0;
            data = new ArrayList<ArrayList<String>>();
            while ((line = br.readLine()) != null) {
                ArrayList<String> arrList = new ArrayList<String>(Arrays.asList(line.split(",")));
                data.add(arrList);

                if(arrList.get(arrList.size()-1).equals("1"))
                    pTotal++;
                else
                    nTotal++;
            }

            size = data.size();

        } catch (Exception e) {
            System.out.println("Error in reading file: " + file.toString());
        }

    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<String> getAttributeList() {
        return attributeList;
    }

    public int getnTotal() {
        return nTotal;
    }

    public int getpTotal() {
        return pTotal;
    }
}
