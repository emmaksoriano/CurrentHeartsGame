package edu.up.cs301.hearts;


import java.util.ArrayList;
import java.util.Arrays;

import edu.up.cs301.card.Card;
import edu.up.cs301.game.infoMsg.GameState;
import edu.up.cs301.slapjack.Deck;

/**
 * Created by emmasoriano on 10/23/17.
 */
//package edu.up.cs301.slapjack;

import edu.up.cs301.card.Card;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Contains the state of a Slapjack game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class HeartsGameState extends GameState
{
    private static final long serialVersionUID = -8269749892027578792L;

    ///////////////////////////////////////////////////
    // ************** instance variables ************
    ///////////////////////////////////////////////////

    // the three piles of cards:
    //  - 0: pile for player 0
    //  - 1: pile for player 1
    //  - 2: the "up" pile, where the top card
    // Note that when players receive the state, all but the top card in all piles
    // are passed as null.
    private Deck[] piles;

    // whose turn is it to turn a card?
    private int toPlay;

    /**
     * Constructor for objects of class SJState. Initializes for the beginning of the
     * game, with a random player as the first to turn card
     *
     */
    public HeartsGameState() {
        // randomly pick the player who starts
        //toPlay = (int)(2*Math.random());
        toPlay = 0;

        // initialize the decks as follows:
        // - each player deck (#0 and #1) gets half the cards, randomly
        //   selected
        // - the middle deck (#2) is empty
        piles = new Deck[3];
        piles[0] = new Deck(); // create empty deck
        piles[1] = new Deck(); // create empty deck
        piles[2] = new Deck(); // create empty deck
        piles[toPlay].add52(); // give all cards to player whose turn it is, in order
        piles[toPlay].shuffle(); // shuffle the cards
        // move cards to opponent, until to piles have ~same size
        while (piles[toPlay].size() >=
                piles[1-toPlay].size()+1) {
            piles[toPlay].moveTopCardTo(piles[1-toPlay]);
        }
    }

    /**
     * Copy constructor for objects of class SJState. Makes a copy of the given state
     *
     * @param orig  the state to be copied
     */
    public HeartsGameState(edu.up.cs301.slapjack.SJState orig) {
        // set index of player whose turn it is
        toPlay = orig.toPlay;
        // create new deck array, making copy of each deck
        piles = new Deck[3];
        piles[0] = new Deck(orig.piles[0]);
        piles[1] = new Deck(orig.piles[1]);
        piles[2] = new Deck(orig.piles[2]);
    }

    /**
     * Gives the given deck.
     *
     * @return  the deck for the given player, or the middle deck if the
     *   index is 2
     */
    public Deck getDeck(int num) {
        if (num < 0 || num > 2) return null;
        return piles[num];
    }

    /**
     * Tells which player's turn it is.
     *
     * @return the index (0 or 1) of the player whose turn it is.
     */
    public int toPlay() {
        return toPlay;
    }

    /**
     * change whose move it is
     *
     * @param idx
     * 		the index of the player whose move it now is
     */
    public void setToPlay(int idx) {
        toPlay = idx;
    }

    /**
     * Replaces all cards with null, except for the top card of deck 2
     */
    public void nullAllButTopOf2() {
        // see if the middle deck is empty; remove top card from middle deck
        boolean empty2 = piles[2].size() == 0;
        Card c = piles[2].removeTopCard();

        // set all cards in deck to null
        for (Deck d : piles) {
            d.nullifyDeck();
        }

        // if middle deck had not been empty, add back the top (non-null) card
        if (!empty2) {
            piles[2].add(c);
        }
    }
}


/*
public class HeartsGameState extends GameState {

    private  Deck[] piles;
    // whose turn is it to turn a card?
    private int toPlay;

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

    public HeartsGameState() {
        // randomly pick the player who starts
        //toPlay = (int)(2*Math.random());
        toPlay = 0;

        // initialize the decks as follows:
        // - each player deck (#0 and #1) gets half the cards, randomly
        //   selected
        // - the middle deck (#2) is empty
        piles = new Deck[3];
        piles[0] = new Deck(); // create empty deck
        piles[1] = new Deck(); // create empty deck
        piles[2] = new Deck(); // create empty deck
        piles[toPlay].add52(); // give all cards to player whose turn it is, in order
        piles[toPlay].shuffle(); // shuffle the cards
        // move cards to opponent, until to piles have ~same size
        while (piles[toPlay].size() >=
                piles[1-toPlay].size()+1) {
            piles[toPlay].moveTopCardTo(piles[1-toPlay]);
        }
    }

    /**
     * HeartsGameState Constructor
     * @param d
     * @param user
     */
/*
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
/*
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

    public Deck getDeck(int num) {
        if (num < 0 || num > 2) return null;
        return piles[num];
    }
    /**
     * deals the players hands
     */
/*
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
/*
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
/*
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
   /* public void setPlayersScore(HeartsPlayer player, int addScore){
        player.setScore(addScore);
    }

    /**
     * get current player
     * @return
     */
   /*
    public HeartsPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * get next player
     * @return
     */
   /*
    public HeartsPlayer getNextPlayer(){
        return nextPlayer;
    }

    /**
     * get current player
     * @return
     */
   /*
    public int getCurrentSuit(){
        return currentSuit;
    }

    /**
     * get round number
     * @return
     */
   /*
    public int getRound(){
        return round;
    }


}
*/