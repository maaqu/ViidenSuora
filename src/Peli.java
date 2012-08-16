
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
    public int vuoro;

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

    public int risti(char[][] lauta, int v) {
        int rVuoro = v;
        if (voittiko(rVuoro) != TYHJA) {
            if (voittiko(rVuoro) == X) {
                return 1;
            }
            if (voittiko(rVuoro) == O) {
                return -1;
            }
            return 0;
        } else {
        int mybest = Integer.MIN_VALUE;
        for (int m = 0; m < KOKO; m++) {
            for (int n = 0; n < KOKO; n++) {
                if (pelilauta[m][n] == TYHJA) {
                    pelilauta[m][n] = O;
                    rVuoro++;
                    int newval = nolla(pelilauta, rVuoro);
                    if (newval > mybest) {
                        mybest = newval;
                    } lauta[m][n] = TYHJA;
                }

            }
        }
        return mybest;
    }
    }

    public int nolla(char[][] lauta, int v) {
       int rVuoro = v;
        if (voittiko(rVuoro) != TYHJA) {
            if (voittiko(rVuoro) == X) {
                return 1;
            }
            if (voittiko(rVuoro) == O) {
                return -1;
            }
            return 0;
        } else {
        int myworst = Integer.MAX_VALUE;
        for (int m = 0; m < KOKO; m++) {
            for (int n = 0; n < KOKO; n++) {
                if (pelilauta[m][n] == TYHJA) {
                    pelilauta[m][n] = X;
                    rVuoro++;
                    int newval = risti(pelilauta, rVuoro);
                    if (newval < myworst) {
                        myworst = newval;
                    }
                    lauta[m][n] = TYHJA;
                }

            }
        }
        return myworst;
    }
    }
    public Sijainti minimaxi(char[][] lauta, int v) {

        int parasArvo = Integer.MAX_VALUE;
        int index = 0;
        Sijainti[] parasSiirto = new Sijainti[9]; // väliaikainen, tietokone päätyy aina ensimmäiseen löydettyyn parhaaseen
        for (int i = 0; i < KOKO; i++) {
            for (int j = 0; j < KOKO; j++) {
                if (lauta[i][j] == TYHJA) {
                    lauta[i][j] = O;
                    int arvo = risti(lauta, v);
                    if (arvo < parasArvo) {
                        parasArvo = arvo;
                        index = 0;
                        parasSiirto[index] = new Sijainti(i, j);
                    } else if (arvo == parasArvo) 
                        parasSiirto[index++] = new Sijainti(i, j);
                    
                    lauta[i][j] = TYHJA;
                }
            }



        }
        if (index > 0) {
            index = (int) Math.random() % index;
        }

        return parasSiirto[index];

    }
    
//    public int minmax(char pelaaja, char[][] lauta) {
//        if (voittiko() != TYHJA) {
//            if (voittiko() == X) {
//                return 1;
//            }
//            if (voittiko() == O) {
//                return -1;
//            }
//            return 0;
//            
//    }
//        for (int i = 0; i < KOKO; i++) {
//            for (int j = 0; j <KOKO; j++) {
//               if (lauta[i][j] == TYHJA) {
//                   lauta[i][j] = pelaaja;
//               }
//            }
//        }
//        if (pelaaja == 'X') {
//            return 
//        }
//    }

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

    char voittiko(int vuo) {


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
        if (vuo == KOKO * KOKO) {
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
                    if (pelaaja == X) {         // pelaaja vaihtuu
                        risti(pelilauta, vuoro);
                        Sijainti location = new Sijainti(minimaxi(pelilauta, vuoro).getVaaka(), minimaxi(pelilauta, vuoro).getPysty());
                        setRuutu(location.getVaaka(), location.getPysty(), 'O');
                        
                    } else {
                        pelaaja = X;
                    }
                }




                if (this.voittiko(vuoro) == 'X' || this.voittiko(vuoro) == 'O') {
                    tulosta();
                    System.out.println("Voittaja : " + this.voittiko(vuoro));
                    return;
                }
                if (this.voittiko(vuoro) == 't') {
                    tulosta();
                    System.out.println("Tasapeli");
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //  setRuutu(jotain, jatka tästä) 
    }
}
