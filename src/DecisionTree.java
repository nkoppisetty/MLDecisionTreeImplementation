import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DecisionTree{
    private Node root;
    private int depth = -1;
    private int noNonLeafNodes;
    private ArrayList<String> attributeListOriginal;
    private ArrayList<Node> nonLeafNodesList;

    public DecisionTree(){
        root = new Node();
    }

    public void buildInfoGainHeuristic(DataSet dataSet){
        attributeListOriginal = dataSet.getAttributeList();
        root = buildTree(dataSet, 1);
    }

    public void buildVarianceImpurityHeuristic(DataSet dataSet){
        attributeListOriginal = dataSet.getAttributeList();
        root = buildTree(dataSet, 2);
    }

    private Node buildTree(DataSet dataSet, int method){
        Heuristics heuristics = new Heuristics();
        double pTotal = dataSet.getpTotal();
        double nTotal = dataSet.getnTotal();
        ArrayList<String> attributeList = dataSet.getAttributeList();
        ArrayList<ArrayList<String>> data = dataSet.getData();

        if (((pTotal == (data.size())) || attributeList.size() < 1)) {
            return new Node("1", true);
        }else if((nTotal == (data.size()))){
            return new Node("0", true);
        }else{
            String attr = heuristics.getBestAttribute(dataSet, attributeListOriginal, method);
            Map<String,ArrayList<ArrayList<String>>> nextDataSetsMap  = heuristics.getnextDataSetsMap(data, attributeListOriginal.indexOf(attr));

            if (nextDataSetsMap.size() < 2){
                if( pTotal > nTotal){
                    return new Node("1", true);
                }else{
                    return new Node("0", true);
                }
            }

            ArrayList<String> modifiedAttrList = new ArrayList<String>();
            for(String val: attributeList){
                if(!val.equalsIgnoreCase(attr)){
                    modifiedAttrList.add(val);
                }
            }

            return new Node(attr, buildTree(new DataSet(nextDataSetsMap.get("0"),modifiedAttrList), method),
                    buildTree(new DataSet(nextDataSetsMap.get("1"),modifiedAttrList), method), false);
        }
    }

    public double getAccuracy(DataSet dataSet) {
        ArrayList<ArrayList<String>> data = dataSet.getData();
        ArrayList<String> attributeList = dataSet.getAttributeList();
        int pTotal = 0;

        for(ArrayList<String> row : data){
            if(isPostive(row, attributeList)){
                pTotal++;
            }
        }

        return (((double) pTotal / (double) (data.size())) * 100.00);
    }

    public boolean isPostive(ArrayList<String> row, ArrayList<String> attributeList){
        Node temp = root;
        while(true){
            String name = temp.getName();
            if(temp.isLeafNode()){
                if(name.equalsIgnoreCase(row.get(row.size()-1))){
                    return true;
                }
                else{
                    return false;
                }
            }

            int index = attributeList.indexOf(name);
            if(row.get(index).equalsIgnoreCase("0")){
                temp = temp.getLeft();
            }else{
                temp = temp.getRight();
            }
        }
    }

    public void replaceNode(int P){
        ArrayList<Node> list = getNonLeafNodesList();
        for(Node n: list){
            if(n.getNodeIndex() == P){
                String leafValueToBeChanged = getMajorityClass(n);
                n.setLeafNode(true);
                n.setLeft(null);
                n.setRight(null);
                n.setName(leafValueToBeChanged);
            }
        }
    }

    public String getMajorityClass(Node node){
        int pTotal = 0;
        int nTotal = 0;
        List<Node> leafNodes = getLeafNodes(node);

        for(Node temp : leafNodes){
            if(node.getName().equalsIgnoreCase("1")){
                pTotal++;
            }
            else{
                nTotal++;
            }
        }

        if(pTotal>nTotal)
            return  "1";
        else
            return "0";
    }

    public int getNonLeafNodes(){
        return getNonLeafNodesList().size();
    }

    public ArrayList<Node> getNonLeafNodesList() {
        noNonLeafNodes = 0;
        setNonLeafNodesList();
        return nonLeafNodesList;
    }

    public void setNonLeafNodesList() {
        nonLeafNodesList = new ArrayList<>();
        setNonLeafNodesList(root);
    }

    public void setNonLeafNodesList(Node temp) {
        if(temp != null && !temp.isLeafNode()){
            nonLeafNodesList.add(temp);
            noNonLeafNodes++;
            temp.setNodeIndex(noNonLeafNodes);
            setNonLeafNodesList(temp.getLeft());
            setNonLeafNodesList(temp.getRight());
        }
    }

    public ArrayList<Node> getLeafNodes(Node node) {
        return getLeafNodes(node, new ArrayList<>());
    }

    public ArrayList<Node> getLeafNodes(Node temp, ArrayList<Node> list) {
        if(temp != null && temp.isLeafNode()){
            list.add(temp);
        }else{
            list = getLeafNodes(temp.getLeft(), list);
            list = getLeafNodes(temp.getRight(), list);
        }
        return list;
    }

    public DecisionTree deepCopy(){
        DecisionTree secondTree = new DecisionTree();
        copyTree(root, secondTree.root);
        return secondTree;
    }

    public void copyTree(Node p, Node q){
        q.setLeafNode(p.isLeafNode());
        q.setName(p.getName());
        if(!p.isLeafNode()){
            q.setLeft(new Node());
            q.setRight(new Node());
            copyTree(p.getLeft(), q.getLeft());
            copyTree(p.getRight(), q.getRight());

        }
    }

    public void print(){
        getNonLeafNodes();
        printDecisionTree(root);
    }

    private void printDecisionTree(Node node){
        ++depth;
        String name = node.getName();
        Node left = node.getLeft();
        Node right = node.getRight();
        int index = node.getNodeIndex();

        if(name.equals("0") || name.equals("1")){
            System.out.print(" : " + name);
        } else {
            System.out.println();

            for(int i = 0; i < depth; ++i) {
                System.out.print(" | ");
            }

            System.out.print(name+"("+index+")"+ " = 0");
        }

        if (left != null) {
            printDecisionTree(left);
            if(name.equals("0") || name.equals("1")){
                System.out.print(" : " + name);
            } else {
                System.out.println();

                for(int i = 0; i < depth; ++i) {
                    System.out.print(" | ");
                }

                System.out.print(name+"("+index+")"+ " = 1");
            }

            printDecisionTree(right);
        }

        --depth;
    }
}
