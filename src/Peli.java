
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peli {

    public char[][] pelilauta;
    char pelaaja;
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char TYHJA = ' ';
    public static final int KOKO = 3; // muutettavissa kysyttäväksi; => n
    public static int vuoro;

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

    public int risti(char[][] lauta) {
        if (voittiko() != TYHJA) {
            if (voittiko() == 'X') {
                return 1;
            }
            if (voittiko() == 'O') {
                return -1;
            }
            return 0;
        }
        int mybest = Integer.MIN_VALUE;
        for (int m = 0; m < KOKO; m++) {
            for (int n = 0; n < KOKO; n++) {
                if (pelilauta[m][n] == TYHJA) {
                    pelilauta[m][n] = 'X';
                    int newval = nolla(pelilauta);
                    if (newval > mybest) {
                        mybest = newval;
                    }
                }

            }
        }
        return mybest;
    }

    public int nolla(char[][] lauta) {
        if (voittiko() != TYHJA) {
            if (voittiko() == 'X') {
                return 1;
            }
            if (voittiko() == 'O') {
                return -1;
            }
            return 0;
        }
        int myworst = Integer.MAX_VALUE;
        for (int m = 0; m < KOKO; m++) {
            for (int n = 0; n < KOKO; n++) {
                if (pelilauta[m][n] == TYHJA) {
                    pelilauta[m][n] = 'O';
                    int newval = risti(pelilauta);
                    if (newval > myworst) {
                        myworst = newval;
                    }
                }

            }
        }
        return myworst;
    }

    public void tulosta() {
        System.out.println("  0   1   2");
        for (int i = 0; i < KOKO; i++) {
            for (int j = 0; j < KOKO; j++) {
                if (j == 0) {
                    System.out.print(i);
                }
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
        if (vuoro == KOKO * KOKO) {
            return 't';
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
                    int vaaka = new Integer(in.readLine());
                    System.out.println("Mikä sarake ");
                    int pysty = new Integer(in.readLine());

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
