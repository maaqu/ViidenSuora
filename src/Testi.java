/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Testi {

    public static Peli uusi = new Peli();
    public static int syvyys;
    public static int[] sijainti = new int[2];

    public static int risti(Peli tilanne) {
        Peli nyt = tilanne;
        if (nyt.voittiko(nyt.vuoro) != nyt.TYHJA) {
            if (nyt.voittiko(nyt.vuoro) == 'X') {
                return 1;
            } else if (nyt.voittiko(nyt.vuoro) == '0') {
                return -1;
            } else {
                return 0;
            }
        }
        int myBest = Integer.MIN_VALUE;
        int uusiarvo;
        for (int i = 0; i < nyt.KOKO; i++) {
            for (int j = 0; j < nyt.KOKO; j++) {

                if (nyt.pelilauta[i][j] == uusi.TYHJA) {
                    nyt.setRuutu(i, j, tilanne.pelaaja);
                    uusiarvo = nolla(nyt);
                    if (uusiarvo > myBest) {
                        myBest = uusiarvo;
                        sijainti[0] = i;
                        sijainti[1] = j;
                    }
                }
            }
        }

        return myBest;
    }

    public static int nolla(Peli tilanne) {
        Peli nyt = tilanne;

        if (nyt.voittiko(nyt.vuoro) != nyt.TYHJA) {
            if (nyt.voittiko(nyt.vuoro) == 'X') {
                return 1;
            } else if (nyt.voittiko(nyt.vuoro) == '0') {
                return -1;
            } else {
                return 0;
            }
        }
        int myWorst = Integer.MAX_VALUE;
        //       int[] placeOfWorst = new int[3];
        //       placeOfWorst[2] = myWorst;
        int uusiarvo;

        for (int i = 0; i < nyt.KOKO; i++) {
            for (int j = 0; j < nyt.KOKO; j++) {

                if (nyt.pelilauta[i][j] == uusi.TYHJA) {
                    nyt.setRuutu(i, j, tilanne.pelaaja);
                    uusiarvo = risti(nyt);
                    if (uusiarvo < myWorst) {
                        myWorst = uusiarvo;
                        //                     sijainti[0] = i;
                        //                     placeOfWorst[1] = j;
                    }
                }
            }
        }

        return myWorst;
    }

    public static void main(String[] args) {

        Peli ristinolla = new Peli();
        ristinolla.pelaa();

    }
}