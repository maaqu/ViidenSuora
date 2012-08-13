/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class Testi {
    
    private Node solmu;
    private Puu puu;
    int rivi;   //ei tarvita mutta jätin viel hetkeks kun ton rekursio-
    int sarake; //puun talletus viel vaiheessa.
    private Peli todellinen; //nimi pitänee vaihtaa
    private char pelaaja; //HUOM! en ole tehnyt vielä niin, että vuoron mukaan
                           //vaihtuisi X ja O
    
    public Testi() {
        this.rivi = 0;
        this.sarake = 0;
    }
    
    public Testi(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
    }
    public void asetaMatriisiin() {
        
    }
    
    public void kayLapi() { //tänne tullaan jostain päämetodista
        for(int r=0; r < todellinen.KOKO; r++) {
            for(int s=0; s<todellinen.KOKO; s++) {
                if(todellinen.pelilauta[r][s] == todellinen.TYHJA) {
                    todellinen.setRuutu(r, s, pelaaja);
                    kayLapi();
                }
                //tehdään tarkistukset ollaanko reunoissa tai lopussa,
                //ja muutetaan sen mukaan r(=riviä) ja s(=saraketta), todnäk
                //pitää tehdä jo ennen tota if:iä.
                else if(r == todellinen.KOKO-1 && s < todellinen.KOKO-1) {//ollaanko oikeassa reunassa muttei vikassa
                    //koska ollaan täällä, on reunassa jo merkki.
                    rivi++;
                    sarake=0;
                    kayLapi();
                }
                
            }
        }
    }
    
}
