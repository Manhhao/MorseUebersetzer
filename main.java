import java.util.*;

public class main
{
    MorseBaum baum;
    public main()
    {
        baum = new MorseBaum();

        /* Füge alle Buchstaben des Alphabets hinzu */
        baum.fuegeMorseCodeZuBaum(".-", "A");
        baum.fuegeMorseCodeZuBaum("-...", "B");
        baum.fuegeMorseCodeZuBaum("-.-.", "C");
        baum.fuegeMorseCodeZuBaum("-..", "D");
        baum.fuegeMorseCodeZuBaum(".", "E");
        baum.fuegeMorseCodeZuBaum("..-.", "F");
        baum.fuegeMorseCodeZuBaum("--.", "G");
        baum.fuegeMorseCodeZuBaum("....", "H");
        baum.fuegeMorseCodeZuBaum("..", "I");
        baum.fuegeMorseCodeZuBaum(".--", "J");
        baum.fuegeMorseCodeZuBaum("-.-", "K");
        baum.fuegeMorseCodeZuBaum(".-..", "L");
        baum.fuegeMorseCodeZuBaum("--", "M");
        baum.fuegeMorseCodeZuBaum("-.", "N");
        baum.fuegeMorseCodeZuBaum("---", "O");
        baum.fuegeMorseCodeZuBaum(".--.", "P");
        baum.fuegeMorseCodeZuBaum("--.-", "Q");
        baum.fuegeMorseCodeZuBaum(".-.", "R");
        baum.fuegeMorseCodeZuBaum("...", "S");
        baum.fuegeMorseCodeZuBaum("-", "T");
        baum.fuegeMorseCodeZuBaum("..-", "U");
        baum.fuegeMorseCodeZuBaum("...-", "V");
        baum.fuegeMorseCodeZuBaum(".--", "W");
        baum.fuegeMorseCodeZuBaum("-..-", "X");
        baum.fuegeMorseCodeZuBaum("-.--", "Y");
        baum.fuegeMorseCodeZuBaum("--..", "Z");
        baum.wurzel.element = "";
        
        /* Konsoleneingaben lesen */
        Scanner eingabeLeser = new Scanner(System.in);
        System.out.print("Morsecode zu Text (1) oder Text zu Morsecode (2), bitte entsprechende Zahl eingeben: ");
        String eingabe = eingabeLeser.nextLine();
        
        if (eingabe.equals("1")) {
            System.out.print("Geben Sie den zu übersetzenden Morse ein: ");
            String morse = eingabeLeser.nextLine();
            System.out.println(baum.uebersetzeSatz(morse, baum));    
        }
        else if (eingabe.equals("2")) {
            System.out.print("Geben Sie den zu übersetzenden Text ein: ");
            String text = eingabeLeser.nextLine();
            System.out.println(baum.encodeViaRelations(text));
        }        
    }
}