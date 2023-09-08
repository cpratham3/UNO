import java.util.*;

public class Deck {

//    Card[] deck = new Card[Value.values().length*Color.values().length];
    List<Card> theDeck = new ArrayList<>();
    public Deck(){
//        int index = 0;
        for (Color color:Color.values()) {
            for (Value val: Value.values() ){
                theDeck.add(new Card(color,val));
            }
        }
        System.out.println(theDeck);
    }
    public List<Card> getDeck() {
        return this.theDeck;
    }
    public void setDeck(List<Card> deck) {
        this.theDeck = deck;
    }
    public void shuffleDeck(){
        Collections.shuffle(theDeck);
    }
}
