// TODO: Decide whether to improve the security of the properties

/*
    This class define the node of the BinaryTree class
*/

class Node {
    // Each node will hold a string value and pointers to left and right nodes below it
    String value;
    Node left;
    Node right;

    // Constructor method for the Node class
    Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}