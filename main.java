import java.util.*;

public class main
{
    MorseBaum baum;
    public main()
    {
        baum = new MorseBaum();

        /* Füge alle Buchstaben des Alphabets hinzu */
        baum.fuegeMorseCodeZuBaum("._", "A");
        baum.fuegeMorseCodeZuBaum("_...", "B");
        baum.fuegeMorseCodeZuBaum("_._.", "C");
        baum.fuegeMorseCodeZuBaum("_..", "D");
        baum.fuegeMorseCodeZuBaum(".", "E");
        baum.fuegeMorseCodeZuBaum(".._.", "F");
        baum.fuegeMorseCodeZuBaum("__.", "G");
        baum.fuegeMorseCodeZuBaum("....", "H");
        baum.fuegeMorseCodeZuBaum("..", "I");
        baum.fuegeMorseCodeZuBaum(".___", "J");
        baum.fuegeMorseCodeZuBaum("_._", "K");
        baum.fuegeMorseCodeZuBaum("._..", "L");
        baum.fuegeMorseCodeZuBaum("__", "M");
        baum.fuegeMorseCodeZuBaum("_.", "N");
        baum.fuegeMorseCodeZuBaum("___", "O");
        baum.fuegeMorseCodeZuBaum(".__.", "P");
        baum.fuegeMorseCodeZuBaum("__._", "Q");
        baum.fuegeMorseCodeZuBaum("._.", "R");
        baum.fuegeMorseCodeZuBaum("...", "S");
        baum.fuegeMorseCodeZuBaum("_", "T");
        baum.fuegeMorseCodeZuBaum(".._", "U");
        baum.fuegeMorseCodeZuBaum("..._", "V");
        baum.fuegeMorseCodeZuBaum(".__", "W");
        baum.fuegeMorseCodeZuBaum("_.._", "X");
        baum.fuegeMorseCodeZuBaum("_.__", "Y");
        baum.fuegeMorseCodeZuBaum("__..", "Z");
        baum.wurzel.element = "";
        /* Test um die Funktion zu überprüfen*/
        // System.out.println(baum.uebersetze("__.."));

        // System.out.println(uebersetzeSatz(".... ._ ._.. ._.. ___",baum));

        /* Konsoleneingaben lesen */
        Scanner eingabeLeser = new Scanner(System.in);
        System.out.println("Geben Sie einen Morsecode ein, ein Strich ist _ (Unterstrich) und ein Punkt ein ganz normaler .");
        String eingabe = eingabeLeser.nextLine();
        System.out.println(encodeViaRelations(eingabe));
    }

    /* 
     * Das Problem: die Methode in der MorseBaum Klasse ist nicht dazu fähig mehrere Buchstaben zu Übersetzen,
     * sie kann an den Leerzeichen nicht "aufhören" und dann ein neuen Buchstaben anfangen. Also machen wir das hier. 
     * Dafür muss der Methode ein String gegeben werden, in der die einzelnen Morsezeichen durch Leerzeichen getrennt werden. 
     * Es wird anschließend am Leerzeichen der String in verschiedene Strings gesplitted um Sie einzeln zu Übersetzen und am Ende zueinanderzufügen.
     * Leider hatte ich keine Idee mehr, wie ich sogar einzelne Wörter trennen könnte, aber naja, wenigstens wird übersetzt.
     */
    String uebersetzeSatz(String pEingabe, MorseBaum pBaum) {
        /* Der String in dem sich später alle Zeichen befinden sollen */
        String uebersetzung = "";

        /* 
         * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String), der String wird am Leerzeichen getrennt 
         * und in ein Array von Strings gegeben
         */
        String[] zeichenArray = pEingabe.split(" ");

        /* 
         * Eine sogenannte for each-Schleife, hier wird nicht wie bei einer normalen for-Schleife mithilfe einer Variable iteriert, sondern es wird durch 
         * alle Objekte eines Arrays iteriert, viel einfacher als eine for-Schleife dafür zu benutzen.
         */
        for (String a : zeichenArray) {
            /* Addiere einfach den übersetzten Buchstaben auf die bisher übersetzten Buchstaben, es sollte am Ende jeder Buchstabe übersetzt sein */
            uebersetzung += pBaum.uebersetze(a);
        }

        return uebersetzung;
    }

    public Knoten preOrderSearch(Knoten pNode, String pVal) {
        if(pNode != null) {
            if (pNode.gibWert().equals(pVal)) {
                return pNode;
            }
            else {
                Knoten recursiveLeft = preOrderSearch(pNode.gibLinks(), pVal);

                if (recursiveLeft != null)
                    return recursiveLeft;

                return preOrderSearch(pNode.gibRechts(), pVal);
            }
        }
        else
            return null;
    }

    public String encodeViaRelations(String pText) {
        String encodedText = "";

        for (int i = 0; i < pText.length(); i++) {
            String morseKey = "";
            ArrayList<Relation> relations = new ArrayList<>();
            String currentLetter = pText.substring(i, i + 1);
            Knoten node = preOrderSearch(baum.wurzel, currentLetter);

            while (node != null) {
                if (node.gibRelation() == Relation.LEFT) {
                    relations.add(Relation.LEFT);
                    node = node.gibParent();
                }
                else if (node.gibRelation() == Relation.RIGHT) {
                    relations.add(Relation.RIGHT);
                    node = node.gibParent();
                }
                else if (node.gibRelation() == Relation.ROOT){
                    break;
                }
            }

            for (int j = relations.size() - 1; j >= 0; j--) {
                if (relations.get(j) == Relation.LEFT)
                    morseKey += ".";
                else if (relations.get(j) == Relation.RIGHT)
                    morseKey += "-";
            }

            encodedText += morseKey += " ";
        }
        return encodedText;
    }
}
