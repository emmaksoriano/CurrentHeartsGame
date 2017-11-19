package edu.up.cs301.hearts;

import java.util.Random;

import edu.up.cs301.card.Card;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Updated by S. Seydlitz on 11/17/17
 */

public class EasyAI extends HeartsPlayer {
//public class EasyAI extends GameComputerPlayer {
    /*

    public EasyAI(String playerName) {
        super(playerName);
    }

    protected void receiveInfo(GameInfo info) {

    }

    int clubs = 0;
    int spades = 1;
    int diamonds = 2;
    int hearts = 4;
    String thisGuy = this.getName();
    HeartsCard[] currentHand = thisGuy.getHand();
    HeartsGameState thisTime = new HeartsGameState(0, thisGuy);
    int baseSuit = thisTime.getCurrentSuit();
    boolean heartsPlayed = false;
    HeartsCard chosenCard;
    String baseN;
    public String[] faceValues = {"Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

    public void strategy() {
//pick a card at random from EasyAI's card deck
        //Remember, there are three different AI's, so there are three different decks to keep track of
        Random ran = new Random();
        //x is rank, y is suit
        int x = ran.nextInt(14);
        int y = ran.nextInt(5);
        if (baseSuit == clubs) {
            baseN = "Clubs";
        }
        if (baseSuit == spades) {
            baseN = "Spades";
        }
        if (baseSuit == diamonds) {
            baseN = "Diamonds";
        }
        if (baseSuit == hearts) {
            baseN = "Hearts";
        }
        if (baseSuit == -1) {
            int z = ran.nextInt(4);
            chosenCard = new HeartsCard(x, z);
        }//our card will be first

        //check and see if the AI player has a card of the suit that was originally played. If not, play any card

        /////check and see if the player has this card in their hand.
        if (checkIfFaceInHand(baseN) == true) {
            chosenCard = new HeartsCard(x, baseSuit);
        } else {
            chosenCard = new HeartsCard(x, y);
        }

    }

    public HeartsCard playCard() {
        if (checkIfCardinHand(chosenCard) == true) {
            //if they have this card, then take it away from the AI player's hand!
            return chosenCard;
        } else {
            strategy();
        }

    }

//Added methods from HeartsPlayer class

    public Card[] getMyPass(){
        return myPass;
    }


    public Card[] getHand(){
        return (Card[]) hand.toArray();
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public boolean isMyTurn(){
        return myTurn;
    }

    public void setMyPass(Card[] cards){
        myPass = cards;
    }
    public void setIsWinner(boolean initWinner){
        isWinner= initWinner;
    }

    /**
     * Set hand to given list of cards
     * @param initHand - shouldn't be more then
     */
    public void setHand(Card[] initHand){
        int i;
        for (i = 0; i < initHand.length; i++){
            hand.add(initHand[i]);
        }
        collection= (Card[]) hand.toArray();
    }

    public void setName(String initName){
        name = initName;
    }

    public void setScore(int initScore){
        score = score + initScore;
    }

    public void setMyTurn(boolean initMyTurn){
        myTurn = initMyTurn;
    }

    public void threeCardPass(Card[] pass, HeartsPlayer p){
        //pass cards to appropriate player
        p.setHand(pass);

        //remove cards passed to another player from hand
        for(Card c: hand){
            for(int i=0; i<pass.length; i++){
                if(c.equals(pass[i])){
                    hand.remove(pass[i]);
                }
            }
        }
    }

    public boolean checkIfCardinHand(Card card){
        for(Card c: hand){
            if(c.equals(card)){
                return true;
            }
        }
        return false;
    }
}
}
