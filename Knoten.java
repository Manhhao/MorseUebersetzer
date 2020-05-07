/* Eine simple Knoten-Klasse für einen Binären Baum
 * String element:          Eine Variable zuständig für das Speichern des Wertes,
 *                          der Wurzel, ein String
 * Knoten links, rechts:    Die nächsten Knoten
 */
class Knoten {
    String element;
    Knoten links, rechts, parent;
    Relation relationZuParent;
    
    Knoten(String o, Knoten pParent, Relation pRelation) {  
        element = o;
        links = rechts = null;
        parent = pParent;
        relationZuParent = pRelation;
    }
    
    String gibWert() {
        return element;
    }
    
    Relation gibRelation() {
        return relationZuParent;
    }
    
    Knoten gibParent() {
        return parent;
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