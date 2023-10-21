import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;


public class Round {

    private static ArrayList<Player> players;
    private Deck deck;
    private Stack<Card> discard;

    private static final boolean darkmode = false;

    private Player playcurrentPlayer;
    private final int DEALTCARDS = 7;

    Round(ArrayList<Player> players){
        this.players = players;
        deck = new Deck();
        discard = new Stack<Card>();
    }

    public Deck getDeck() {
        return deck;
    }

    public void distributeHand(){
        int i = 0;
        while (i < DEALTCARDS) {
            for (Player player : players){
                player.getHand().addCard(deck.pop());
            }
            i++;
        }
    }

    public Card getDiscardStack(){

        return discard.pop();
    }

    public void playRound(){

        playcurrentPlayer = players.get(1);


        Scanner user_card = new Scanner(System.in);
        System.out.println("Input a card: ");
        System.out.println(playcurrentPlayer.getHand().toString());
        int Card = user_card.nextInt();


        boolean flag = true;

        while (flag){

       

        }






    }

    public Card playCard(int user){


        Card addCard;

        addCard = playcurrentPlayer.getHand().getCard(user);

        discard.add(addCard);


        return addCard;
    }


    public void displayCard(){}


    public boolean checkCard(Card card1, Card card2){

        return false;
    }

    public boolean takeCardFromDeck(Player player, int n){

        int i = 0;

        if (n > 0) {

            while (i <= n) {

                player.getHand().addCard(deck.pop());

                i++;
            }
            return true;
        }

        return false;
    }

    public void skipPlayer() {}

    public void reverse(){}

    public void wildCard(Card card){}

    public boolean checkWinner(){return false;}

    public void calculatePoints(){}


    public static void main (String args[]){
        
        Player AJ = new Player("AJ");
        Player Jason = new Player("Jason");
        Player Zarif = new Player("Zarif");
        Player Arun = new Player("Arun");

        ArrayList<Player> players1 = new ArrayList<>();
        players1.add(AJ);
        players1.add(Jason);
        players1.add(Zarif);
        players1.add(Arun);


        Round round = new Round(players1);
        round.distributeHand();




        }




    }

