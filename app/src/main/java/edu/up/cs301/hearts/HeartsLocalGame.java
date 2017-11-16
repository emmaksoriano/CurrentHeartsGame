package edu.up.cs301.hearts;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * This class checks legality of players moves and finds the winner of the round.
 * Created by emmasoriano on 10/19/17.
 */

public class HeartsLocalGame extends LocalGame {
    HeartsGameState currentGame;

    private final static int WIN_TARGET = 100;

    //constructor
    public HeartsLocalGame(){


    }

    /**
     *
     * validCard method checks if the card played by currentPlayer is legal
     *
     */
    public boolean validCard(Card card){

        boolean valid = false;

        //check if they played a card of the same suit as first card played,
        if(card.getSuitValue().equals(currentGame.table.cardsPlayed[0].getSuitValue())){
            valid = true;
        }
        // if not, check if they have a card of that suit,
        else{
           for (Card c : currentGame.currentPlayer.hand){
               if(c.getSuitValue().equals(currentGame.table.cardsPlayed[0].getSuitValue())){
                   valid = false;
               }
           }
        }

        return valid;
    }

    /**
     *  validTurn method checks if its legal for a player to play a card
     *
     */
    public boolean validTurn(HeartsPlayer player){
        return player.myTurn;
    }

    /**
     * checks who's turn it is to play a card
     */
    public HeartsPlayer checkTurn(){
        HeartsPlayer currentPlayer = null;

        for(int i = 0; i<currentGame.players.length; i++){
            if(currentGame.players[i].myTurn == true){
                currentPlayer = currentGame.players[i];
            }
        }

        currentGame.setCurrentPlayer(currentPlayer);

        return currentPlayer;
    }

    /**
     *   The winRound method determines which player won the round
     *   and sets their isWinner boolean to true.
     */
    public void winRound(){
        //find suit of first card played
        String suit = currentGame.table.cardsPlayed[0].getSuitValue();
        int highestFace = currentGame.table.cardsPlayed[0].getCardValue();

        //find highest card of suit played
        for(int i=0; i<currentGame.table.cardsPlayed.length; i++){
            if(currentGame.table.cardsPlayed[i].getCardValue()>highestFace){
             highestFace = currentGame.table.cardsPlayed[i].getCardValue();
            }
        }

        //find which player played that card
        Card winningCard = new Card(highestFace,currentGame.table.cardsPlayed[0].suitValueIndex);
        for(HeartsPlayer p: currentGame.players){
            if(p.checkIfCardinHand(winningCard)){
                p.setIsWinner(true);
            }
        }

    }

    public void CardPass(){

        /*

        //ask user to select three cards then hit "pass" button
        // TODO Fix this
        if(false){
            //have a round int that tells us which way to pass
            if(currentGame.round == 0) {
                //pass right
                currentGame.players[0].threeCardPass(currentGame.players[0].getMyPass(),currentGame.players[1]);
                currentGame.players[1].threeCardPass(currentGame.players[1].getMyPass(),currentGame.players[2]);
                currentGame.players[2].threeCardPass(currentGame.players[2].getMyPass(),currentGame.players[3]);
                currentGame.players[3].threeCardPass(currentGame.players[3].getMyPass(),currentGame.players[0]);

            }

            if(currentGame.round == 1) {
                //pass right
                currentGame.players[0].threeCardPass(currentGame.players[0].getMyPass(),currentGame.players[3]);
                currentGame.players[1].threeCardPass(currentGame.players[1].getMyPass(),currentGame.players[0]);
                currentGame.players[2].threeCardPass(currentGame.players[2].getMyPass(),currentGame.players[1]);
                currentGame.players[3].threeCardPass(currentGame.players[3].getMyPass(),currentGame.players[2]);

            }

            if(currentGame.round == 2) {
                //pass across table
                currentGame.players[0].threeCardPass(currentGame.players[0].getMyPass(),currentGame.players[2]);
                currentGame.players[1].threeCardPass(currentGame.players[1].getMyPass(),currentGame.players[3]);
                currentGame.players[2].threeCardPass(currentGame.players[2].getMyPass(),currentGame.players[0]);
                currentGame.players[3].threeCardPass(currentGame.players[3].getMyPass(),currentGame.players[1]);

            }

        }
        */

    }

    public int calculatePoints(){
        int points = 0;

        for(Card c: currentGame.table.cardsPlayed){
            //add one point each time a heart is on the table
            if(c.suitValue.equals("Hearts")){
             points++;
            }
            //add 13 points if the queen of spades is on the table
            else if(c.suitValue.equals("Spades")&& c.faceValue == 10){
                points=+13;
            }
        }

        return points;
    }


    public void updateScore(){
        int points = calculatePoints();
        for(int i = 0; i<currentGame.players.length; i++){
            if(currentGame.players[i].isWinner == true){
                currentGame.players[i].setScore(points);
            }
        }
    }


    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    protected boolean canMove(int playerIdx) {
        return false;
    }

    protected String checkIfGameOver() {
        return null;
    }

    protected boolean makeMove(GameAction action) {
        return false;
    }
}
