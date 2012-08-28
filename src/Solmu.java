
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Solmu {
    
    public int moveBox;
    public int point;
    public Peli board;
    public Solmu parent;
    public ArrayList<Solmu> lapsiSolmu = new ArrayList<Solmu>();

    public Solmu() {
        this.board = new Peli();
    }

    public Solmu(Peli board) {
        this.board = board;
    }

    public void AddLapsi(Solmu node) {
        lapsiSolmu.add(node);
        node.Parent(this);
    }

    private void Parent(Solmu node) {
        this.parent = node;
    }

    public void kopioi(Solmu n) {
        this.point=n.point;

        this.board.kopioi(n.board);

    }


}
