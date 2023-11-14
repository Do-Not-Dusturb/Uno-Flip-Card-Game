import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Controller {
    // baaaaah,

    private UnoGUI unoGUI;
    private Uno unoModel;

    private boolean isPlayerLocked = false;

    public Controller(UnoGUI gui, Uno uno) {

        this.unoGUI = gui;
        this.unoModel = uno;


        this.unoGUI.addBuildDeckListener(new updateDeckListener());

        this.unoModel.testRound();


        this.unoGUI.addStartGameListener(new playGameButtonListener());
        this.unoGUI.addPlayers(new addPlayersListener());

        this.unoGUI.addNextPlayerListener(new nextPlayerButtonListener());


        // initially update discard
        //this.unoGUI.updateDiscard(unoModel.currentRound.discard.peek());
    }

    public class addPlayersListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (unoGUI.numFields < 4) {
                unoGUI.addPlayerField();
            }
            if (unoGUI.numFields >= 4) {
                unoGUI.addPlayer.setEnabled(false); // Disable the add player button
            }
        }
    }

    public class playGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Start the game
            unoGUI.startGame();

            // Clear existing cards/buttons from the GUI
            unoGUI.clearPlayerCards();

            // Add new cards to the GUI
            for (int i = 0; i < unoModel.currentRound.currentPlayer.getHand().getSize(); i++) {
                unoGUI.addCard(unoModel.currentRound.currentPlayer.getHand().getCard(i));
            }

            unoGUI.addPlayCardListener(unoModel.currentRound.currentPlayer.getHand(), new listenForCardPlayed());
            // update discard ui
            unoGUI.updateDiscard(unoModel.currentRound.discard.peek().getImageFilePath());
        }
    }

        private class updateDeckListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isPlayerLocked == false){
                    //handle playing card
                    System.out.println("Removes Card From Deck");
                    unoModel.currentRound.drawCurrPlayer();
                    System.out.println("Deck Size: " + unoModel.currentRound.deck.getSize());
                    System.out.println("HandSize: " + unoModel.currentRound.currentPlayer.getHand().getSize());

                    //unoModel.currentRound.currentPlayer.getHand();
                    unoGUI.addCard(unoModel.currentRound.currentPlayer.getHand().getCard(unoModel.currentRound.currentPlayer.getHand().getSize() - 1));
                    unoGUI.addPlayCardListener(unoModel.currentRound.currentPlayer.getHand(), new listenForCardPlayed());

                    isPlayerLocked = true;
                }
            }
        }

        public class listenForCardPlayed implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                JButton button = (JButton) e.getSource();
                int buttonIndex = Integer.parseInt(button.getName());
                System.out.println("Card Clicked: " + unoModel.currentRound.currentPlayer.getHand().getCard(buttonIndex));
                unoModel.currentRound.setPlayCardIndex(buttonIndex);
                if (unoModel.currentRound.cardPlayedLogic() && isPlayerLocked == false) {
                    isPlayerLocked = true;
                    if (unoModel.currentRound.Remove_card.getTypeLight().equals(Card.TypeLight.WILD_DRAW_FOUR) || unoModel.currentRound.Remove_card.getTypeLight().equals(Card.TypeLight.WILDTWO)) {

                        if (unoModel.currentRound.Remove_card.equals(Card.TypeLight.WILDTWO)) {
                            unoModel.currentRound.drawCard(2);
                            unoGUI.wildCardGui();
                            unoGUI.redWildCardButtonListener(new playRedWildCard());
                            unoGUI.blueWildCardButtonListener(new playBlueWildCard());
                            unoGUI.yellowWildCardButtonListener(new playYellowWildCard());
                            unoGUI.greenWildCardButtonListener(new playGreenWildCard());
                        }
                        else if (unoModel.currentRound.Remove_card.getTypeLight().equals(Card.TypeLight.WILD_DRAW_FOUR)) {
                            unoModel.currentRound.drawCard(4);
                            unoGUI.wildCardGui();
                            unoGUI.redWildCardButtonListener(new playRedWildCard());
                            unoGUI.blueWildCardButtonListener(new playBlueWildCard());
                            unoGUI.yellowWildCardButtonListener(new playYellowWildCard());
                            unoGUI.greenWildCardButtonListener(new playGreenWildCard());
                        }
                    }

                    unoGUI.updatePlayerCardsRemove(button, unoModel.currentRound.currentPlayer.getHand());
                    // update discard ui
                    unoGUI.updateDiscard(unoModel.currentRound.discard.peek().getImageFilePath());
                }
            }

        }


        public class playRedWildCard implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("playRedWildCard");
                unoModel.currentRound.discard.peek().setColorLight("Red");

                unoGUI.wildCardFrame.setVisible(false);

                unoGUI.discardInfo(unoModel.currentRound.deck.peek());



            }
        }

        public class playBlueWildCard implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("playBlueWildCard");
                System.out.println("analysis discard peek: " + unoModel.currentRound.discard.peek());

                unoModel.currentRound.discard

                unoModel.currentRound.discard.peek().setColorLight("Blue");

                unoGUI.wildCardFrame.setVisible(false);

                unoGUI.discardInfo(unoModel.currentRound.deck.peek());
            }
        }

        public class playYellowWildCard implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("playYellowWildCard");

                unoModel.currentRound.discard.peek().setColorLight("Yellow");

                unoGUI.wildCardFrame.setVisible(false);

                unoGUI.discardInfo(unoModel.currentRound.deck.peek());
            }
        }

        public class playGreenWildCard implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("playGreenWildCard");

                unoModel.currentRound.discard.peek().setColorLight("Green");

                unoGUI.wildCardFrame.setVisible(false);

                unoGUI.discardInfo(unoModel.currentRound.deck.peek());
            }
        }

        public class nextPlayerButtonListener implements  ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                isPlayerLocked = false;
                System.out.println("player turn changed");
                unoModel.currentRound.nextPlayer();
                unoGUI.displayCurrentPlayer(unoModel.currentRound.getPlayers().indexOf(unoModel.currentRound.currentPlayer));
                unoGUI.clearPlayerCards();
                // Add new cards to the GUI
                for (int i = 0; i < unoModel.currentRound.currentPlayer.getHand().getSize(); i++) {
                    unoGUI.addCard(unoModel.currentRound.currentPlayer.getHand().getCard(i));
                }
                unoGUI.addPlayCardListener(unoModel.currentRound.currentPlayer.getHand(), new listenForCardPlayed());
            }
        }


        public static void main(String args[]) {
            Controller controller = new Controller(new UnoGUI(), new Uno());

        }
    }
