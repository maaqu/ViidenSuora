
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
        pelaaja = O;
    }

    public boolean setRuutu(int pysty, int vaaka, char pelaaja) {
        if (pelilauta[pysty][vaaka] == TYHJA) {
            pelilauta[pysty][vaaka] = pelaaja;
            vuoro++;
            return true;
        } else {
            return false;
        }
    }

    public int nolla(char[][] lauta, int v) {
        int rVuoro = v;
        if (voittiko(rVuoro) != TYHJA) {
            if (voittiko(rVuoro) == O) {
                return -1;
            }
            if (voittiko(rVuoro) == X) {
                return 1;
            }
            return 0;
        } else {
            int myworst = Integer.MAX_VALUE;
            for (int m = 0; m < KOKO; m++) {
                for (int n = 0; n < KOKO; n++) {
                    if (pelilauta[m][n] == TYHJA) {
                        pelilauta[m][n] = O;
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
                        pelilauta[m][n] = X;
                        rVuoro++;
                        int newval = nolla(pelilauta, rVuoro);
                        if (newval > mybest) {
                            mybest = newval;
                        }
                        lauta[m][n] = TYHJA;
                    }

                }
            }
            return mybest;
        }
    }

    public Sijainti minimaxi(char[][] lauta, int v) {
        int parasArvo = Integer.MIN_VALUE;
        int index = 0;
        int arvo;
        Sijainti[] parasSiirto = new Sijainti[9]; // väliaikainen, tietokone päätyy aina ensimmäiseen löydettyyn parhaaseen
        for (int i = 0; i < KOKO; i++) {          // ei tajua 1,1 -> 1,2 -> 0,0 tilannetta oikein
            for (int j = 0; j < KOKO; j++) {
                if (lauta[i][j] == TYHJA) {
                    lauta[i][j] = O;
                    if (voittiko(v, lauta) == 'O') {
                        arvo = -1;
                    } else {
                        arvo = risti(lauta, v);
                    }
                    if (arvo > parasArvo) {
                        parasArvo = arvo;
                        for (int u = 0; u < 9; u++) {
                            parasSiirto[u] = null;
                        }
                        index = 0;
                        parasSiirto[index] = new Sijainti(i, j);
                    } else if (arvo == parasArvo) {
                        parasSiirto[index++] = new Sijainti(i, j);
                    }

                    lauta[i][j] = TYHJA;
                }
            }



        }
        if (index > 0) {
            index = (int) (Math.random() * index);
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

    char voittiko(int vuo, char[][] plauta) {


        if (plauta[1][0] == plauta[0][0] && plauta[1][0] == plauta[2][0]) {
            return plauta[1][0];
        }
        if (plauta[1][1] == plauta[0][1] && plauta[1][1] == plauta[2][1]) {
            return plauta[1][1];
        }
        if (plauta[1][2] == plauta[0][2] && plauta[1][2] == plauta[2][2]) {
            return plauta[1][2];
        }
        if (plauta[0][1] == plauta[0][0] && plauta[0][0] == plauta[0][2]) {
            return plauta[0][0];
        }
        if (plauta[1][1] == plauta[1][0] && plauta[1][0] == plauta[1][2]) {
            return plauta[1][1];
        }
        if (plauta[2][1] == plauta[2][0] && plauta[2][0] == plauta[2][2]) {
            return plauta[2][0];
        }
        if (plauta[0][0] == plauta[1][1] && plauta[0][0] == plauta[2][2]) {
            return plauta[0][0];
        }
        if (plauta[0][2] == plauta[1][1] && plauta[0][2] == plauta[2][0]) {
            return plauta[0][2];
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
                while (!kelpaavaSiirto) {
                    Sijainti location = new Sijainti(minimax(pelilauta, vuoro).getPysty(), minimax(pelilauta, vuoro).getVaaka());
                    setRuutu(location.getPysty(), location.getVaaka(), 'X');
                    voittoTarkistus();
                    // kysytään koordinaatteja kunnes saadaan pelialueella oleva paikka
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
                    voittoTarkistus();
                    // pelaaja vaihtuu

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //  setRuutu(jotain, jatka tästä) 
    }

    public void voittoTarkistus() {
        if (this.voittiko(vuoro) == 'X' || this.voittiko(vuoro) == 'O') {
            tulosta();
            System.out.println("Voittaja : " + this.voittiko(vuoro));
            System.exit(0);
        }
        if (this.voittiko(vuoro) == 't') {
            tulosta();
            System.out.println("Tasapeli");
            System.exit(0);
        }
    }

    public Sijainti minimax(char[][] pelilautsa, int v) {
        Sijainti[] bestMove = new Sijainti[9];
        int i;
        int j;
        int bestValue = Integer.MAX_VALUE, index = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (pelilautsa[i][j] == ' ') {
                    pelilautsa[i][j] = 'O';
                    int value = maxSearch(pelilautsa, v);
                    if (value < bestValue) {
                        bestValue = value;
                        index = 0;
                        bestMove[index] = new Sijainti(i, j);
                    } else if (value == bestValue) {
                        bestMove[index++] = new Sijainti(i, j);
                    }
                    pelilautsa[i][j] = ' ';
                }
            }
        }
            if (index > 0)
                index = (int) (Math.random() * index);
            
            
        return bestMove[index];
    }
                
    

    public int minSearch(char[][] pelilautsa, int v) {
        int i;
        int j;
        int rVuoro = v;
        if (voittiko(rVuoro) != TYHJA) {
            if (voittiko(rVuoro) == X) {
                return 1;
            }
            if (voittiko(rVuoro) == O) {
                return -1;
            }
            return 0;
        }
        int bestValue = Integer.MAX_VALUE;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {

                if (pelilautsa[i][j] == ' ') {
                    pelilautsa[i][j] = 'O';
                    int value = maxSearch(pelilautsa, v);
                    if (value < bestValue) {
                        bestValue = value;
                    }
                    pelilautsa[i][j] = ' ';
                }
            }
        }


        return bestValue;
    }

    public int maxSearch(char[][] pelilautsa, int v) {
        int i;
        int j;
        int rVuoro = v;
        if (voittiko(rVuoro) != TYHJA) {
            if (voittiko(rVuoro) == X) {
                return 1;
            }
            if (voittiko(rVuoro) == O) {
                return -1;
            }
            return 0;
        }
        int bestValue = Integer.MIN_VALUE;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (pelilautsa[i][j] == ' ') {
                    pelilautsa[i][j] = 'X';
                    int value = minSearch(pelilautsa, v);
                    if (value > bestValue) {
                        bestValue = value;
                    }
                    pelilautsa[i][j] = ' ';
                }
            }
        }
        return bestValue;
    }
}
