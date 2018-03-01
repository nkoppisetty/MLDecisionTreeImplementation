public class Node {
    private Node left;
    private Node right;
    private String name;
    private boolean isLeafNode;
    private int nodeIndex;

    public Node() {
        super();
    }

    public Node(String name, boolean isLeafNode){
        this.name = name;
        this.left = null;
        this.right = null;
        this.isLeafNode = isLeafNode;
    }

    public Node(String name, Node left, Node right, boolean isLeafNode){
        this.name = name;
        this.left = left;
        this.right = right;
        this.isLeafNode = isLeafNode;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }
    public void setLeafNode(boolean leafNode) {
        isLeafNode = leafNode;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}
