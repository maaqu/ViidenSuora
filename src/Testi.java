/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Testi {

    private Peli palautettava;//paras tilanne
    private Peli todellinen; //=pelitilanne
    private char pelaaja;
    int myBest;

    public Testi(Peli tilanne) {
        this.todellinen = tilanne;
        this.palautettava = todellinen;
        this.pelaaja = 'X';
        
    }

    public void tarkistaTilanne() {
        kayLapi();
    }

    public int kayLapi() { 

        for (int r = 0; r < todellinen.KOKO; r++) {
            for (int s = 0; s < todellinen.KOKO; s++) {

                if (palautettava.voittiko() == 'X') {
                    return 1; //return
                } else if (palautettava.voittiko() == '0') {
                    return -1; //return
                } else if (palautettava.voittiko() == 't') {
                    return 0; //return
                
                } else {

                    if (palautettava.pelilauta[r][s] == todellinen.TYHJA) {
                        palautettava.setRuutu(r, s, pelaaja);
                        if (this.pelaaja == 'X') {
                            pelaaja = '0';
                        } else {
                            pelaaja = 'X';
                        }
                        kayLapi();
                        
                    } else {
                        kayLapi();
                    }
                }
            }
        }
        return 88;
    }
}
