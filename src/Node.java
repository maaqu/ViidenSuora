/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Node {
       private Node left; 
    private Node right;
    private int key;   
    private Object value; 

    public Node(int key, Object value, Node left, Node right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int key, Object value) {
        this.key = key;
        this.value = value;
        // left ja right ovat null
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }

    public Object getValue() {
        return value;
    } 

    public void setValue(Object value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String toString() {
        String l, r;
        
        if (left == null)
            l = "null";
        else
            l = left.toString();

        if (right == null)
            r = "null";
        else
            r = right.toString();

        return "Node["+key+","+value+", "+l+", "+r+"]";
    }
    
}
