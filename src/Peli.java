import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peli {

    private char[][] pelilauta;
    char pelaaja;
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char TYHJA = ' ';
    public static final int KOKO = 3; // muutettavissa kysyttäväksi; => n
    public static int vuoro;
    private Scanner reader = new Scanner(System.in);

    public Peli() {

        pelilauta = new char[KOKO][KOKO];

        for (int i = 0; i < pelilauta.length; i++) {
            for (int j = 0; j < pelilauta[i].length; j++) {
                pelilauta[i][j] = TYHJA;
            }
        }
        vuoro = 0;
        pelaaja = X;
    }

    public boolean setRuutu(int vaaka, int pysty, char pelaaja) {
        if (pelilauta[vaaka][pysty] == TYHJA) {
            pelilauta[vaaka][pysty] = pelaaja;
            vuoro++;
            return true;
        } else {
            return false;
        }
    }

    public void tulosta() {
        System.out.println("  0   1   2");
        for (int i = 0; i < KOKO; i++) {
            for (int j = 0; j < KOKO; j++) {
                if (j == 0)
                    System.out.print(i);
                System.out.print(" " + pelilauta[i][j] + " ");
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println(" ---+---+---");
        }

    }

    char voittiko() {

        if (vuoro == KOKO*KOKO)
            return 't';
        if (pelilauta[1][0] == pelilauta[0][0] && pelilauta[1][0] == pelilauta[2][0]) {
            return pelilauta[1][0];
        }
        if (pelilauta[1][1] == pelilauta[0][1] && pelilauta[1][1] == pelilauta[2][1]) {
            return pelilauta[1][1];
        }
        if (pelilauta[1][2] == pelilauta[0][2] && pelilauta[1][2] == pelilauta[2][2]) {
            return pelilauta[1][2];
        }
        if (pelilauta[0][1] == pelilauta[0][0] && pelilauta[0][0] == pelilauta[0][2]) {
            return pelilauta[0][0];
        }
        if (pelilauta[1][1] == pelilauta[1][0] && pelilauta[1][0] == pelilauta[1][2]) {
            return pelilauta[1][1];
        }
        if (pelilauta[2][1] == pelilauta[2][0] && pelilauta[2][0] == pelilauta[2][2]) {
            return pelilauta[2][0];
        }
        if (pelilauta[0][0] == pelilauta[1][1] && pelilauta[0][0] == pelilauta[2][2]) {
            return pelilauta[0][0];
        }
        if (pelilauta[0][2] == pelilauta[1][1] && pelilauta[0][2] == pelilauta[2][0]) {
            return pelilauta[0][2];
        }
        
        return TYHJA;           
    }

    public void pelaa() {
        boolean pelaako = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (pelaako) {

            try {
                boolean kelpaavaSiirto = false;
                while (!kelpaavaSiirto) {     // kysytään koordinaatteja kunnes saadaan pelialueella oleva paikka
                    tulosta();
                    System.out.println("Pelaaja: " + pelaaja);
                    System.out.println("Mikä rivi ");
                    int vaaka = reader.nextInt();
                    System.out.println("Mikä sarake ");
                    int pysty = reader.nextInt();

                    kelpaavaSiirto = setRuutu(vaaka, pysty, pelaaja);
                    if (!pelaako) {
                        System.out.println("Väärä siirto");
                    }
                }


                if (pelaaja == X) {         // pelaaja vaihtuu
                    pelaaja = O;
                } else {
                    pelaaja = X;
                }
                if (this.voittiko() == 'X' || this.voittiko() == 'O') {
                    tulosta();
                    System.out.println("Voittaja : " + this.voittiko());
                    return;
                }
                if (this.voittiko() == 't') {
                    tulosta();
                    System.out.println("Tasapeli");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
