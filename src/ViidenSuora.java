
public class ViidenSuora {


    public static void main(String[] args) {
        System.out.println("Tämä on viiden suora!");

        
       char[][] lauta = { {'O', 'O', ' '}, {'O', 'X', 'X'}, {' ', 'X', 'X'} }; //testailua
        
        Peli ristinolla = new Peli();
//        ristinolla.tulosta(1);
        
        ristinolla.pelaa();
         
    }
}
