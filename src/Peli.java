/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maaku
 */
public class Peli {

    int[][] pelilauta;
    

    public Peli() {

        pelilauta = new int[3][3];
    }

    public void setRuutu(int i, int j, int merkki) {
        if (merkki == 1) {
            pelilauta[i][j] = 1;
        } else if (merkki == 2) {
            pelilauta[i][j] = 2;
        } else {
        }
    }

    public void tulosta() {
        for (int i = 0; i < 3; i++) { // Tässä tapauksessa i < 3, laudan kokoa voidaan muuttaa myöhemmin ja sitoa muuttujaan, jolloin i < n
            System.out.println("");
            for (int j = 0; j < 3; j++) {
                System.out.print("|");
                if (pelilauta[i][j] == 1) {
                    System.out.print("o");
                } else if (pelilauta[i][j] == 2) {
                    System.out.print("x");
                } else {
                    System.out.print(" ");
                }
                if (j == 2) {                   // Samoin j = n-1
                    System.out.print("|");
                }
            }
        }
    }
}
