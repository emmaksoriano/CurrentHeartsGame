package edu.up.cs301.hearts;


import java.util.ArrayList;
import java.util.Arrays;

import edu.up.cs301.card.Card;
import edu.up.cs301.slapjack.Deck;

/**
 * Created by emmasoriano on 10/23/17.
 */

public class HeartsGameState {

    private static final long serialVersionUID = -8269749892027578797L;

    // Declare Instance Variables
    public String userName;
    public HeartsPlayer[] players = new HeartsPlayer[4];
    public HeartsPlayer currentPlayer;
    public HeartsPlayer nextPlayer;
    public CardDeck deck;
    public int playerIndex;
    public int difficulty;
    public int[] currentScores;
    public int currentSuit;
    public int round;
    Table table;

    /**
     * HeartsGameState Constructor
     * @param d
     * @param user
     */
    public HeartsGameState(int d, String user){
        //initialize variables
        difficulty = d;
        userName = user;
        setPlayers();
        setCurrentPlayer(players[0]);
        playerIndex = 0;
        currentScores[0] = 0;
        currentScores[1] = 0;
        currentScores[2] = 0;
        currentScores[3] = 0;
        currentSuit = 1;
        round = 0;
        table = new Table();
        //create a deck of cards
        deck = new CardDeck();
    }




    /**
     * Sets players to
     */
    public void setPlayers(){
        int i;
        players[0] = new HeartsHumanPlayer(userName);
        for(i = 1; i <= 3; i++){
            if(difficulty == 0){
                EasyAI newAI = new EasyAI("Temp Easy AI " + i);
                players[i] = newAI;
            }
            else{
                HardAI newAI = new HardAI("Temp Hard AI " + i);
                players[i] = newAI;
            }
        }
    }

    /**
     * deals the players hands
     */
    public void dealHands(){
        CardDeck[] hand = new CardDeck[4];
        int counter=0;
        deck.shuffle();
        CardDeck copyDeck = deck;
        for(int i= 0; i<deck.size();i++){
            if(i%13==0 && i!=0){
                counter++;
            }
            copyDeck.moveTopCardTo(hand[counter]);
        }

        for(int j = 0; j<players.length; j++){
            players[j].setHand(hand[j]);
        }

    }
//determine who starts
    /**
     * set a given player for who's turn it is
     * @param player
     */
    public void setCurrentPlayer(HeartsPlayer player){
        int i;
        for(i = 0; i < players.length; i++){
            if(players[i].equals(player)){
                playerIndex = i;
            }
        }
        currentPlayer = player;
        setNextPlayer();
    }

    /**
     * sets the next player
     */
    public void setNextPlayer(){
        int i;
        for(i = 0; i < players.length; i++){
            if(players[i].equals(currentPlayer)){
                if(i == 3){
                    nextPlayer = players[0];
                }
                else {
                    nextPlayer = players[i+1];
                }
            }
        }
    }

    /**
     * set given player's score
     * @param player
     * @param addScore
     */
    public void setPlayersScore(HeartsPlayer player, int addScore){
        player.setScore(addScore);
    }

    /**
     * get current player
     * @return
     */
    public HeartsPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * get next player
     * @return
     */
    public HeartsPlayer getNextPlayer(){
        return nextPlayer;
    }

    /**
     * get current player
     * @return
     */
    public int getCurrentSuit(){
        return currentSuit;
    }

    /**
     * get round number
     * @return
     */
    public int getRound(){
        return round;
    }


}
