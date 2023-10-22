import java.util.ArrayList;


/**
 * @author Zarif, Arun, Ajen, Jason
 * @version 1.0
 */
public class Hand {

    private ArrayList<Card> hand;

    public Hand(){

        this.hand = new ArrayList<Card>();

    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card removeCard(Card card){
        hand.remove(card);
        return card;
    }

    public ArrayList<Card> getHandList(){
        return this.hand;
    }
    
    public Card getCard(int card_num){
        return this.hand.get(card_num);
    }

    public ArrayList<Card> getAll(){

        return hand;

    }
    public int getSize(){
        return this.hand.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(" [" + hand.indexOf(card) + "] ");

            sb.append(card.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String args[]){
        Deck deck = new Deck();
        Hand hand = new Hand();
        hand.addCard(deck.pop());
        hand.addCard(deck.pop());
        hand.addCard(deck.pop());
        hand.addCard(deck.pop());
        hand.addCard(deck.pop());
        System.out.println(hand);        
    }
}
