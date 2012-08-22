/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emmivesa
 */
public class SiirronPisteet {

    //luonnostelupaperi.
    //vuoro ja pelilauta pelissä metodin ulottuvilla
    char[][] plauta;
    int[][] arvolauta; //voisko tästä tehdä esim. int-taulukoita
    //tallettavan arraylistin, että sais kaikki arvot talteen?

    void setPisteet(int sijvaaka, int sijpysty, int vuoro) {
        // kun koneen vuoro ja koneella paikkoja
        if (plauta[sijvaaka + 2][sijpysty - 2] == vuoro && //kulmat
                plauta[sijvaaka + 1][sijpysty - 1] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka + 1][sijpysty - 1] == vuoro
                && plauta[sijvaaka + 2][sijpysty - 2] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka + 1][sijpysty + 1] == vuoro
                && plauta[sijvaaka + 2][sijpysty + 2] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka - 1][sijpysty - 1] == vuoro
                && plauta[sijvaaka + 1][sijpysty + 1] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka - 1][sijpysty + 1] == vuoro
                && plauta[sijvaaka + 1][sijpysty - 1] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka][sijpysty - 1] == vuoro && //vaaka
                plauta[sijvaaka][sijpysty + 1] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka][sijpysty + 1] == vuoro
                && plauta[sijvaaka][sijpysty + 2] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka][sijpysty - 1] == vuoro
                && plauta[sijvaaka][sijpysty - 2] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka - 1][sijpysty] == vuoro && //pysty
                plauta[sijvaaka + 1][sijpysty] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka - 1][sijpysty] == vuoro
                && plauta[sijvaaka - 2][sijpysty] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        if (plauta[sijvaaka + 1][sijpysty] == vuoro
                && plauta[sijvaaka + 2][sijpysty] == vuoro) {
            arvolauta[sijvaaka][sijpysty] = 1000;
        }
        //kun koneella vuoro ja vastustajalla paikkoja

        if (plauta[sijvaaka + 2][sijpysty - 2] != vuoro && //kulmat
                plauta[sijvaaka + 1][sijpysty - 1] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka + 1][sijpysty - 1] != vuoro
                && plauta[sijvaaka + 2][sijpysty - 2] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka + 1][sijpysty + 1] != vuoro
                && plauta[sijvaaka + 2][sijpysty + 2] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka - 1][sijpysty - 1] != vuoro
                && plauta[sijvaaka + 1][sijpysty + 1] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka - 1][sijpysty + 1] != vuoro
                && plauta[sijvaaka + 1][sijpysty - 1] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka][sijpysty - 1] != vuoro && //vaaka
                plauta[sijvaaka][sijpysty + 1] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka][sijpysty + 1] != vuoro
                && plauta[sijvaaka][sijpysty + 2] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka][sijpysty - 1] != vuoro
                && plauta[sijvaaka][sijpysty - 2] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka - 1][sijpysty] != vuoro && //pysty
                plauta[sijvaaka + 1][sijpysty] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka - 1][sijpysty] != vuoro
                && plauta[sijvaaka - 2][sijpysty] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }
        if (plauta[sijvaaka + 1][sijpysty] != vuoro
                && plauta[sijvaaka + 2][sijpysty] != vuoro) {
            arvolauta[sijvaaka][sijpysty] = 700;
        }

    }
}
