/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Puu {  //Huomenta darratoveri! 
                    //Testailin aamulla parii juttua mitä en viel oo
                    //pushannu, ni siks tääl nää puu ja solmu, ei
                    //välttämättä tarvita. 
    private Node root;

    public Puu(Node root) {
        this.root = root;
    }

    public Puu() {
        this(null);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String toString() {
        return "Tree["+root+"]";
    }
    
}
