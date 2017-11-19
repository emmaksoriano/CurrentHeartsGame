package edu.up.cs301.hearts;

import java.util.Random;

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
    String thisGuy; //THIS NEEDS TO BE CHANGED!!! this needs to indicate the "name" of the AI, so AI 1, 2, or 3
    HeartsGameState thisTime = new HeartsGameState(0,thisGuy);
    int baseSuit = thisTime.getCurrentSuit();
    boolean heartsPlayed = false;
    HeartsCard chosenCard;
    String baseN;
    public String[] faceValues = {"Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen","King","Ace"};

    public void strategy() {
//pick a card at random from EasyAI's card deck
        //Remember, there are three different AI's, so there are three different decks to keep track of
        Random ran = new Random();
        //x is rank, y is suit
        int x = ran.nextInt(14);
        int y = ran.nextInt(5);
        if(baseSuit==clubs){
            baseN = "Clubs";
        }
        if(baseSuit==spades){
            baseN = "Spades";
        }
        if(baseSuit==diamonds){
            baseN = "Diamonds";
        }
        if(baseSuit==hearts){
            baseN = "Hearts";
        }
        if(baseSuit==-1){
            int z = ran.nextInt(4);
            chosenCard = new HeartsCard(x,z);
        }//our card will be first

        //check and see if a heart card has been played. If not, play a non-heart card. If yes, play any card. If only heart cards are in the player's hand, play any card

        if(heartsPlayed=true){
            /////check and see if the player has this card in their hand. if so, play it and erase it from the hand
            if(checkIfFaceInHand(baseN)==true){
                chosenCard = new HeartsCard(x, baseSuit);
            }
            else{
                chosenCard = new HeartsCard(x,y);
            }

        }
    }

    public void playCard() {
        if(checkIfCardinHand(chosenCard)==true){
            return chosenCard;
        }
        else{
            strategy();
        }

    }
}
