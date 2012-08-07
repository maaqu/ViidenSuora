
public class ViidenSuora {


    public static void main(String[] args) {
        System.out.println("Tämä on viiden suora!");

        
//        int[][] lauta = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} }; //testailua
        
        Peli ristinolla = new Peli();
        ristinolla.tulosta();
        ristinolla.setRuutu(0, 0, 1);
        ristinolla.setRuutu(0, 1, 2);
        ristinolla.tulosta();
         
    }
}
