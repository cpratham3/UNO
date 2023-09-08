import javax.crypto.spec.PSource;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        instantiateDeck();

    }

    public static void instantiateDeck() {
//        int i = 0;
        ArrayList<Card> playerCards = new ArrayList<>();
        ArrayList<Card> computerCards = new ArrayList<>();

        Deck deck = new Deck();
        deck.shuffleDeck();
        System.out.println(deck.getDeck());
        for (int i = 0; i < 7; i++) {
            playerCards.add(deck.getDeck().get(i));
            deck.getDeck().remove(deck.getDeck().get(i));
        }
        for (int i = 7; i < 14; i++) {
            computerCards.add(deck.getDeck().get(i));
            deck.getDeck().remove(deck.getDeck().get(i));
        }


        Card firstCardinPile = deck.getDeck().get(14);

        deck.getDeck().remove(firstCardinPile);
        System.out.println("First card on board: "+firstCardinPile);
        System.out.println("Deck now: "+deck.getDeck());
        System.out.println("Now length of deck: "+deck.getDeck().size());
        System.out.println(deck.getDeck().contains(firstCardinPile));


        Hand pHand = new Hand(playerCards);
        Hand cHand = new Hand(computerCards);

        Player player1 = new Player("Player1",pHand);

        Player computer = new Player("Computer",cHand);

        System.out.println("Player 1 Hand : "+player1.getHand());
        System.out.println("Computer Hand: "+computer.getHand());
        startGame(player1,computer,firstCardinPile,deck);


    }
    public static void startGame(Player p,Player c,Card firstCardinPile,Deck deck){
        HashMap<Integer,Card> cardIndexHolder = new HashMap<>();
        Scanner input = new Scanner(System.in);
        boolean won = false;
        Card firstCard =  firstCardinPile;
        System.out.println("\n************Game Board**********\n");
        Player currentPlayer = p;
        Player opponent = c;

//        System.out.println(firstCard);

        while(!won){
            System.out.println(firstCard);
            System.out.println("It's "+ currentPlayer.getName()+ "'s "+ "turn!");
            System.out.println( currentPlayer.getName()+ "'s "+"hand: ");
//            System.out.println(p.getHand().seeHand());
            int cardNumber = 1;
            for (Card card: currentPlayer.getHand().seeHand()) {
                System.out.println(String.valueOf(cardNumber) + " " +card );
                cardIndexHolder.put(cardNumber,card);
                cardNumber++;
            }
            System.out.println("Choose to Play: ");
            int cardChosen = input.nextInt();
            if(canPlayCard(firstCard,cardChosen,cardIndexHolder)){
                System.out.println("yes can play");
                System.out.println(firstCard);
                System.out.println(cardIndexHolder.get(cardChosen));
                System.out.println(cardChosen);
                System.out.println("Card removed from hand "+ currentPlayer.getHand().seeHand().get(cardChosen-1));
                currentPlayer.getHand().seeHand().remove(cardChosen-1);
                System.out.println("Now "+ currentPlayer.getName()+ "'s" +" hand "+ currentPlayer.getHand().seeHand());
            }
            else{
                boolean cardFound = false;
                System.out.println(currentPlayer.getHand().seeHand().contains(firstCard));
                for (Card cardInHand: currentPlayer.getHand().seeHand() ) {
                    if ((cardInHand.getValue().equals(firstCard.getValue()) || (cardInHand.getColor().equals(firstCard.getColor())))) {
                        cardFound = true;
                        break;
                    }
                }
                if (cardFound){
                    System.out.println("Card cannot be played...play another!");
                    continue;
                }
                else{
                    System.out.println("Necessary card not in your deck...drawing....");
                    for (Card deckCheck: deck.getDeck() ) {
                        if ((deckCheck.getValue().equals(firstCard.getValue())||(deckCheck.getColor().equals(firstCard.getColor())))){
                            currentPlayer.getHand().seeHand().add(deckCheck);
                            deck.getDeck().remove(deckCheck);
                            System.out.println("Added card "+ deckCheck);
                            System.out.println("Now Deck "+ deck.getDeck());
                            System.out.println("Now Hand "+ currentPlayer.getHand().seeHand());

                        }
                    }
                }
            }
            if (currentPlayer.getHand().seeHand().isEmpty()){
                won = true;
            }
            firstCard = cardIndexHolder.get(cardChosen);
            System.out.println("neeche");
            Player tempPlayer = currentPlayer;
            currentPlayer = opponent;
            opponent = tempPlayer;

        }
    }

    public static boolean canPlayCard(Card currentCard, int cardChosen, Map<Integer, Card> cardIndexHolder){
        return cardIndexHolder.get(cardChosen).getColor().equals(currentCard.getColor())||cardIndexHolder.get(cardChosen).getValue().equals(currentCard.getValue());
    }

}
//omkar ki branch