
public class ViidenSuora {


    public static void main(String[] args) {
        System.out.println("Tämä on kolmen suora!");

        
       char[][] lauta = { {'O', 'O', ' '}, {'O', 'X', 'X'}, {' ', 'X', 'X'} }; //testailua
        
        Peli ristinolla = new Peli();
        ristinolla.pelaa();
         
    }
}
