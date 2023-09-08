import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cardsInHand = new ArrayList<>();

    public Hand(ArrayList<Card> cardsInHand){
        this.cardsInHand = cardsInHand;
    }
    public void addCardToHand(Card card){
        this.cardsInHand.add(card);
    }
    public void removeCardFromHand(Card card){
        this.cardsInHand.remove(card);
    }
    public ArrayList<Card> seeHand(){
        return this.cardsInHand;
    }

    @Override
    public String toString() {
        return this.cardsInHand.toString();
    }
}
