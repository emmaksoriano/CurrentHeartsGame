package edu.up.cs301.hearts;

/**
 * Created by emmasoriano on 10/19/17.
 */

public class Table {

    Card[] cardsPlayed = new Card[4];

    //Constructor
    public Table(){
        //initialize "empty" table
        for(int i=0; i<4; i++){
            cardsPlayed[i]=null;
        }

    }

    public void addCard(Card card){
        for(int i=0; i<4; i++){
            if(cardsPlayed[i] == null){
                cardsPlayed[i]=card;
                break;
            }
        }
    }

    public Card[] getTable(){
        return cardsPlayed;
    }

    public void clearTable(){
        for(int i=0; i<4; i++){
            cardsPlayed[i]=null;
        }

    }
}
