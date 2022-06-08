/*
    This class define the node of the BinaryTree class
*/

class Node {
    // Each node will hold a string value and pointers to left and right nodes below it
    private String value;   
    private Node left;
    private Node right;

    // Constructor method for the Node class
    Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /* Getters and setters */

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}