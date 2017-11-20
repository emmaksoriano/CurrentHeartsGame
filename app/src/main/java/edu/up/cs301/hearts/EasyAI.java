package edu.up.cs301.hearts;

import java.util.Random;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.card.Suit;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Updated by S. Seydlitz on 11/17/17
 */

public class EasyAI extends GameComputerPlayer {

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
    CardDeck currentHand = new CardDeck(thisGuy.getHand); //THIS!!!
    HeartsGameState thisTime = new HeartsGameState(0,thisGuy); //THIS!!
    Suit baseSuit = thisTime.getCurrentSuit(); //THIS!
    boolean heartsPlayed = false;
    Card chosenCard;
    CardDeck hand;
    Card[] collection;
    Card[] myPass = new Card[3];
    boolean myTurn = false;
    boolean isWinner = false;
    boolean hasTwoOfClubs = false;
    int score = 0;
    String name;

    public void strategy() {

        Rank[] ranks = new Rank[13];
        Suit[] suits = new Suit[4];
        ranks[0].fromChar('2');
        ranks[1].fromChar('3');
        ranks[2].fromChar('4');
        ranks[3].fromChar('5');
        ranks[4].fromChar('6');
        ranks[5].fromChar('7');
        ranks[6].fromChar('8');
        ranks[7].fromChar('9');
        ranks[8].fromChar('T');
        ranks[9].fromChar('J');
        ranks[10].fromChar('Q');
        ranks[11].fromChar('K');
        ranks[12].fromChar('A');
        suits[0].fromChar('C');
        suits[1].fromChar('S');
        suits[2].fromChar('D');
        suits[3].fromChar('H');

        //pick a card at random from EasyAI's card deck
        //Remember, there are three different AI's, so there are three different decks to keep track of
        Random rand = new Random();
        //x is rank, y is suit
        int x = rand.nextInt(14);
        int y = rand.nextInt(5);
        /*
        if(baseSuit==clubs){
            baseSuit = suits[0];
        }
        if(baseSuit==spades){
            baseSuit = "Spades";
        }
        if(baseSuit==diamonds){
            baseSuit = "Diamonds";
        }
        if(baseSuit==hearts){
            baseSuit = "Hearts";
        }
        */
        if(baseSuit==null){
            /*
            int o,p;
            for(o=0;o<13;o++) {
                for(p=0;p<4;p++){
                    chosenCard = new Card(ranks[o],suits[p]);
                }
            }
            */
            chosenCard = new Card(ranks[x],suits[y]);
        }//our card will be first

        //check and see if the AI player has a card of the suit that was originally played. If not, play any card

        /////check and see if the player has this card in their hand.
        else if(checkIfFaceInHand(baseSuit)==true){
            chosenCard = new Card(ranks[x], baseSuit);
        }
        else{
            chosenCard = new Card(ranks[x],suits[y]);
        }

    }

    public Card playCard() {
        if(checkIfCardinHand(chosenCard)==true){
            //if they have this card, then take it away from the AI player's hand!
            //to do this we need to make sure that we can get the array of cards in the player's hand!
            currentHand.remove(chosenCard);
            return chosenCard;
        } else {
            strategy();
        }
        return null;
    }

//Added methods from HeartsPlayer class

    public Card[] getMyPass(){
        return myPass;
    }


    public Card[] getHand(){
        return (Card[]) hand.cards.toArray();
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
        collection= (Card[]) hand.cards.toArray();
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

    /*
    public void threeCardPass(Card[] pass, HeartsPlayer p){
        //pass cards to appropriate player
        p.setHand(pass);

        //remove cards passed to another player from hand
        for(Card c: hand){
            for(int i=0; i<pass.length; i++){
                if(c.equals(pass[i])){
                    hand.cards.remove(pass[i]);
                }
            }
        }
    }


    */
    public boolean checkIfCardinHand(Card card){
        for(Card c: currentHand.cards){
            if(c.equals(card)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfFaceInHand(Suit face){
        for(Card c: currentHand.cards){
            if(c.getSuit().equals(face)){
                return true;
            }
        }
        return false;
    }
}

