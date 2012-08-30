
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Peli {

    public static char[][] pelilauta;
    char pelaaja;
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char TYHJA = ' ';
    public static int KOKO; // muutettavissa kysyttäväksi; => n
    public static int vuoro;
    public static int alpha = Integer.MIN_VALUE;
    public static int beta = Integer.MAX_VALUE;
    public static int syvyys = 3;

    public Peli() {
        this.KOKO = 3;
        pelilauta = new char[KOKO][KOKO];

        for (int i = 0; i < pelilauta.length; i++) {
            for (int j = 0; j < pelilauta[i].length; j++) {
                pelilauta[i][j] = TYHJA;
            }
        }
        vuoro = 0;
        pelaaja = O;
    }

    public Peli(int koko) {
        this.KOKO = koko;
        pelilauta = new char[KOKO][KOKO];

        for (int i = 0; i < pelilauta.length; i++) {
            for (int j = 0; j < pelilauta.length; j++) {
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
        int huonoinArvo = Integer.MIN_VALUE;
        int index = 0;
        int arvo;
        Sijainti[] parasSiirto = new Sijainti[9]; // väliaikainen, tietokone päätyy aina ensimmäiseen löydettyyn parhaaseen
        for (int i = 0; i < KOKO; i++) {          // ei tajua 1,1 -> 1,2 -> 0,0 tilannetta oikein
            for (int j = 0; j < KOKO; j++) {
                if (lauta[i][j] == TYHJA) {
                    lauta[i][j] = X;
                    if (voittiko(v, lauta) == 'X') {
                        arvo = 1;
                    } else {
                        arvo = nolla(lauta, v);
                    }
                    if (arvo > huonoinArvo) {
                        huonoinArvo = arvo;
                        for (int u = 0; u < 9; u++) {
                            parasSiirto[u] = null;
                        }
                        index = 0;
                        parasSiirto[index] = new Sijainti(i, j);
                    } else if (arvo == huonoinArvo) {
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

    public void tulosta(int koko) { //tulee olemaan parametritön

        System.out.print("  ");
        for (int l = 0; l < KOKO; l++) {
            System.out.print(l + "   ");
        }
        System.out.println();

        for (int i = 0; i < KOKO; i++) {
            for (int j = 0; j < KOKO; j++) {
                if (j == 0) {
                    System.out.print(i);
                }
                System.out.print(" " + pelilauta[i][j] + " ");
                if (j != KOKO - 1) {
                    System.out.print("|");
                }
            }
            if (i != KOKO - 1) {
                System.out.println();
                int apu = 1;
                System.out.print(" ");

                while (apu <= KOKO) {
                    System.out.print("---");
                    if (apu < KOKO) {
                        System.out.print("+");
                    }
                    apu++;
                }
            }
            System.out.println();
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
                    Sijainti location = new Sijainti(minimaxi(pelilauta, vuoro).getPysty(), minimaxi(pelilauta, vuoro).getVaaka());
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
        public void pelaa2() {
        boolean pelaako = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (pelaako) {


            try {

                boolean kelpaavaSiirto = false;
                while (!kelpaavaSiirto) {
                    Sijainti pleissi = new Sijainti(move(pelilauta, vuoro).getPysty(), move(pelilauta, vuoro).getVaaka());
                    setRuutu(pleissi.getPysty(), pleissi.getVaaka(), 'X');
                    tulosta();
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

        public Sijainti move(char[][] lauta, int v) {
        int parasArvo = -10;
        int arvo;
        int index = 0;
        Sijainti[] parasMuuvi = new Sijainti[9];
        for (int i = 0; i < KOKO; i++) {          // ei tajua 1,1 -> 1,2 -> 0,0 tilannetta oikein
            for (int j = 0; j < KOKO; j++) {
                if (lauta[i][j] == TYHJA) {
                    lauta[i][j] = X;
                    if (voittiko(v, lauta) == 'X') {
                        arvo = 1;
                    } else {
                       arvo = alphabeta(lauta, syvyys, v, alpha, beta, true);
                    }
                    if (arvo > parasArvo) {
                        parasArvo = arvo;
                       for (int u = 0; u < 9; u++) {
                            parasMuuvi[u] = null;
                        }
                        index = 0;
                        parasMuuvi[index] = new Sijainti(i, j);
                    } else if (arvo == parasArvo) {
                        parasMuuvi[index++] = new Sijainti(i, j);
                    }

                    lauta[i][j] = TYHJA;
                }
            }



        }
        if (index > 0) {
            index = (int) (Math.random() * index);
        }

        return parasMuuvi[index];

    }
    
        
        

    public int alphabeta(char[][] lauta, int syvyys, int vuoro, int alpha, int beta, boolean min) {
        int rVuoro = vuoro;
        if (syvyys == 0 || vuoro == 9) {
            int bestPoeng = metodiJokaLaskeeKyseisenLautaTilanteenPisteet2(lauta);
            return bestPoeng;
        } else {
            if (min) {
                for (int i = 0; i < KOKO; i++) {
                    for (int j = 0; j < KOKO; j++) {
                        if (lauta[i][j] == TYHJA) {
                            lauta[i][j] = O;
                            rVuoro++;
                        int preBeta = alphabeta(lauta, syvyys - 1, rVuoro, alpha, beta, false);
                        lauta[i][j] = TYHJA;
                        if (preBeta < beta) {
                            beta = preBeta;
                        }
                        if (beta <= alpha)
                            break;
                        
                        
                        
                    }
                }
                }return beta;

            } else {
                for (int n = 0; n < KOKO; n++) {
                    for (int m = 0; m < KOKO; m++) {
                        if (lauta[n][m] == TYHJA) {
                            lauta[n][m] = X;
                            rVuoro++;
                        int preAlpha = alphabeta(lauta, syvyys - 1, rVuoro, alpha, beta, false);
                        lauta[n][m] = TYHJA;
                        if (preAlpha > alpha) {
                            alpha = preAlpha;
                        }
                        
                        if (beta <= beta) {
                            break;
                        }
                        
                    }
                }
                }
                return alpha;
            
        }
    }
    }

    private int metodiJokaLaskeeKyseisenLautaTilanteenPisteet2(char[][] plauta) {
        return (int)(Math.random()*2)-1;
    }
    
    private int metodiJokaLaskeeKyseisenLautaTilanteenPisteet(char[][] plauta) {

        int arvo = 0;

        for (int i = 0; i < KOKO; i++) {
            for (int j = 0; j < KOKO; j++) {
                
                if (plauta[i][j] == TYHJA) {
                    // kun koneen vuoro ja koneella paikkoja
                    if (plauta[j + 2][i - 2] == vuoro && //kulmat
                            plauta[j + 1][i - 1] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j + 1][i - 1] == vuoro
                            && plauta[j + 2][i - 2] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j + 1][i + 1] == vuoro
                            && plauta[j + 2][i + 2] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j - 1][i - 1] == vuoro
                            && plauta[j + 1][i + 1] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j - 1][i + 1] == vuoro
                            && plauta[j + 1][i - 1] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j][i - 1] == vuoro && //vaaka
                            plauta[j][i + 1] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j][i + 1] == vuoro
                            && plauta[j][i + 2] == vuoro) {
                        if(vuoro==X)
                            arvo += 1000;
                        else
                            arvo += -1000;
                    }
                    if (plauta[j][i - 1] == vuoro
                            && plauta[j][i - 2] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j - 1][i] == vuoro && //pysty
                            plauta[j + 1][i] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j - 1][i] == vuoro
                            && plauta[j - 2][i] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    if (plauta[j + 1][i] == vuoro
                            && plauta[j + 2][i] == vuoro) {
                        if(vuoro==X)
                            arvo = 1;
                        else
                            arvo = -1;
                    }
                    //kun koneella vuoro ja vastustajalla paikkoja

                    if (plauta[j + 2][i - 2] != vuoro && //kulmat
                            plauta[j + 1][i - 1] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j + 1][i - 1] != vuoro
                            && plauta[j + 2][i - 2] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j + 1][i + 1] != vuoro
                            && plauta[j + 2][i + 2] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j - 1][i - 1] != vuoro
                            && plauta[j + 1][i + 1] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j - 1][i + 1] != vuoro
                            && plauta[j + 1][i - 1] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j][i - 1] != vuoro && //vaaka
                            plauta[j][i + 1] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j][i + 1] != vuoro
                            && plauta[j][i + 2] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j][i - 1] != vuoro
                            && plauta[j][i - 2] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j - 1][i] != vuoro && //pysty
                            plauta[j + 1][i] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j - 1][i] != vuoro
                            && plauta[j - 2][i] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                    if (plauta[j + 1][i] != vuoro
                            && plauta[j + 2][i] != vuoro) {
                        if(vuoro==X)
                            arvo = 0;
                    }
                }
            }
        }
        return arvo;
    }
}
