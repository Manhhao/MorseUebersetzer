import java.util.*;
/* 
 * Eine Morsebaum Klasse:
 * Als Wert gespeichert sind nur die Buchstaben, der Morse ist mit dem Weg
 * entlang des Astes dargestellt. Entlang des linken Astes stellt ein Punkt dar,
 * entlang des rechten Astes stellt ein Strich dar.
 */

class MorseBaum {
    Knoten wurzel;
    MorseBaum() {
        wurzel = new Knoten(null, null, Relation.ROOT);
    }

    /* 
     * Die Methode um den Morsecode dem Baum zu übergeben. Funktioniert wie folgt:
     * Der Methode wird sowohl der Buchstabe des Morsecodes gegeben, als auch der Code selbst.
     * Der Code wird nicht in den Knoten gespeichert, er wird lediglich genutzt um entlang des Weges
     * zu gehen. Dabei ist links = Punkt, rechts = Strich. Der Buchstabe wird im Knoten gespeichert.
     * Die Methode funktioniert nach dem Prinzip, jeden Buchstaben des Morsecodes einzeln anzusehen und dann
     * zu entscheiden ob man links oder rechts weitergeht. Kommt man dabei an einen ungültigen Knoten, wird ein neuer erstellt.
     * Benutzt wird dafür eine for-Schleife und der Java-Befehl String.substring(int beginIndex, int endIndex)
     * Benutzte Hilfen: https://beginnersbook.com/2013/12/java-string-substring-method-example/
     */
    void fuegeMorseCodeZuBaum(String morseCode, String buchstabe) {
        /* speichert die aktuelle Position, für den Schleifendurchlauf */
        Knoten position = wurzel;

        /* 
         * die for-Schleife:
         * Sie muss solange ausgeführt werden bis der String durchgelaufen wurde, um die genaue Dauer zu ermitteln,
         * wurde die Methode substring näher untersucht, sie funktioniert wie folgt:
         * Um den Buchstaben an der Position n (der erste Buchstabe ist 1 nicht 0) auszugeben, muss dem Parameter begIndex (n - 1) gegeben werden, da begIndex als
         * "alles danach" definiert ist. Beim ersten wäre begIndex also 0. endIndex ist dann einfach n, endIndex ist als "bis hierhin, inklusive Position" definiert.
         * Die Schleife muss also bei 0 starten um den ersten Buchstaben abzudecken. Sie endet bei morsecode.length, i beim letzten Durchlauf die Länge l des Strings minus 1 betragen wird,
         * und durch das Benutzen des substring mit i + 1 also genau der letzte Buchstabe ausgegeben wird.
         */
        for (int i = 0; i < morseCode.length(); i++) {
            String weg = morseCode.substring(i, i + 1);

            /* Falls das nächste Zeichen ein Punkt ist */
            if (weg.equals(".")) {
                /* Prüfe zunächst ob links gültig und somit überhaupt benutzbar ist, wenn nicht mach den Knoten gültig */
                if (!position.hatLinks())
                    position.links = new Knoten(null, position, Relation.LEFT);

                /* Gehe zum linken Knoten und führe die Schleife eventuell weiter aus */
                position = position.gibLinks();

                // System.out.println("Gehe links"); <- war nur zum testen
            }
            /* Falls das nächste Zeichen ein Strich ist */
            else if (weg.equals("-")) {
                /* Prüfe zunächst ob rechts gültig und somit überhaupt benutzbar ist, wenn nicht mach den Knoten gültig */
                if (!position.hatRechts())
                    position.rechts = new Knoten(null, position, Relation.RIGHT);

                /* Gehe zum Rechten Knoten und führe die Schleife eventuell weiter aus */
                position = position.gibRechts();

                // System.out.println("Gehe rechts"); <- war nur zum testen
            }
        }
        /* Der Weg ist zuende gelaufen, an diese Position gehört also der Buchstabe! */
        position.element = buchstabe;
    }

    String uebersetzeSatz(String pMorseCode, MorseBaum pBaum) {
        /* Der String in dem sich später alle Zeichen befinden sollen */
        String uebersetzung = "";

        /* 
         * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String), der String wird am Leerzeichen getrennt 
         * und in ein Array von Strings gegeben
         */
        String[] zeichenArray = pMorseCode.split(" ");

        /* 
         * Eine sogenannte for each-Schleife, hier wird nicht wie bei einer normalen for-Schleife mithilfe einer Variable iteriert, sondern es wird durch 
         * alle Objekte eines Arrays iteriert, viel einfacher als eine for-Schleife dafür zu benutzen.
         */
        for (String a : zeichenArray) {
            /* speichert die aktuelle Position, für den Schleifendurchlauf */
            Knoten position = wurzel;
            boolean istGültigerBuchstabe = true;
            for (int i = 0; i < a.length(); i++) {
                String weg = a.substring(i, i + 1);

                /* Falls das nächste Zeichen ein Punkt ist */
                if (weg.equals(".")) {
                    /* 
                     * Prüfe zunächst ob links gültig und somit überhaupt benutzbar ist, falls nicht gibt es an dieser Stelle 
                     * keinen Buchstaben, alle Knoten mit Buchstaben sind bereits erstellt 
                     */
                    if (!position.hatLinks()) {
                        istGültigerBuchstabe = false;
                        break;
                    }
                    else { 
                        /* Gehe zum linken Knoten und führe die Schleife eventuell weiter aus */
                        position = position.gibLinks();

                        // System.out.println("Gehe links"); <- war nur zum testen
                    }
                }
                /* Falls das nächste Zeichen ein Strich ist */
                else if (weg.equals("-")) {
                    /* 
                     * Prüfe zunächst ob rechts gültig und somit überhaupt benutzbar ist, falls nicht gibt es an dieser Stelle 
                     * keinen Buchstaben, alle Knoten mit Buchstaben sind bereits erstellt 
                     */
                    if (!position.hatRechts()) {
                        istGültigerBuchstabe = false;
                        break;
                    }
                    else {
                        /* Gehe zum Rechten Knoten und führe die Schleife eventuell weiter aus */
                        position = position.gibRechts();

                        // System.out.println("Gehe rechts"); <- war nur zum testen
                    }
                }

            }
            /* Der Weg ist zuende gelaufen, sollte die boolean true sein, so gibt es einen Buchstaben und wir sollten etwas zurückgeben können */
            if (istGültigerBuchstabe)
                uebersetzung += (String)position.element; /* element muss auf String recasted werden um ihn zurückgeben zu können */
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
            Knoten node = preOrderSearch(this.wurzel, currentLetter);

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
