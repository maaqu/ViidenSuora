//
///**
// *
// * @author Maaku
// */
//public class Pelitulostin {
//
//    int[][] lauta;
//
//    public Pelitulostin(int[][] tilanne) {
//
//        this.lauta = tilanne;
//    }
//    /*
//     * Tulostaa laudan komentoriville, muuttaen ykköset risteiksi ja nollat
//     * o:ksi ja "ruuduttaa"
//     */
//    public void tulosta(int[][] lauta) {
//        for (int i = 0; i < 3; i++) { // Tässä tapauksessa i < 3, laudan kokoa voidaan muuttaa myöhemmin ja sitoa muuttujaan, jolloin i < n
//            System.out.println("");
//            for (int j = 0; j < 3; j++) {
//                System.out.print("|");
//                System.out.print(lauta[i][j]);
//                if (j == 2) {                   // Samoin j = n-1
//                    System.out.print("|");
//                if (lauta[i][j] == 1)
//                    System.out.print("o");
//                else if (lauta[i][j] == 2)
//                    System.out.println("x");
//                else
//                    System.out.println("");
//                if (j == 2) {                   // Samoin j = n-1
//                    System.out.print("|");
//                }
//            }
//        }
//
//    }
//}
