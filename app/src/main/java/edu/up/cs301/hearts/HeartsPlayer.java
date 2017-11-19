package edu.up.cs301.hearts;

import java.util.ArrayList;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Rank;
import edu.up.cs301.card.Suit;


/**
 * Created by emmasoriano on 10/19/17.
 */

public class HeartsPlayer {

    ArrayList<Card> hand ;
    Card[] collection;
    Card[] myPass = new Card[3];
    boolean myTurn = false;
    boolean isWinner = false;
    boolean hasTwoOfClubs = false;
    int score = 0;
    String name;



    //Constructor
    public HeartsPlayer(String playerName){

        //set players name
        name = playerName;

        //determines if player has the starting card
        //Clubs, spades, diamonds, hearts
        HeartsCard twoOfClubs = new HeartsCard(2, 0);
        hasTwoOfClubs = checkIfCardinHand(twoOfClubs);
    }


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
    public void setHand(CardDeck initHand){
        int i;
        Card c;
        for (i = 0; i < initHand.size(); i++){
            c = initHand.peekAtTopCard();
            hand.add(c);
            initHand.removeTopCard();
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

    public void threeCardPass(CardDeck pass, HeartsPlayer p){
        //pass cards to appropriate player
        p.setHand(pass);
        CardDeck copyPass = pass;
        //remove cards passed to another player from hand
        for(Card c: hand){
            for(int i=0; i<pass.size(); i++){
                if(c.equals(copyPass.peekAtTopCard())){
                    hand.remove(copyPass.peekAtTopCard());
                    copyPass.removeTopCard();
                }
            }
        }
    }

    public boolean checkIfCardinHand(HeartsCard card){
        for(Card c: hand){
            if(c.equals(card)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfFaceInHand(String face){
        for(Card c: hand){
            if(hand.contains(face)){
                return true;
            }
        }
        return false;
    }

}
