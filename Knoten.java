/* Eine simple Knoten-Klasse für einen Binären Baum
 * Object element:          Eine Variable zuständig für das Speichern des Wertes,
 *                          der Wurzel, variabler Datentyp
 * Knoten links, rechts:    Die nächsten Knoten
 */
class Knoten {
    Object element;
    Knoten links, rechts;

    Knoten(Object o) {  
        element = o;
        links = rechts = null;
    }
    
    Knoten gibLinks() {
        return links;
    }
    
    Knoten gibRechts() {
        return rechts;
    }
    
    boolean hatLinks() {
       return (links != null);
    }
    
    boolean hatRechts() {
        return (rechts != null);
    }
}