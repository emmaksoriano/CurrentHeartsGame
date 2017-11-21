package edu.up.cs301.hearts;

import edu.up.cs301.card.Card;
import edu.up.cs301.card.Suit;
import edu.up.cs301.game.GamePlayer;

/**
 * Created by emmasoriano on 10/19/17.
 */

public class Table {

    public Card[] cardsPlayed = new Card[4];
    public Suit SuitIndex;

    //Constructor
    public Table(){
        //initialize "empty" table
        for(int i=0; i<4; i++){
            cardsPlayed[i]=null;
        }

    }

    public void addCard(Card card, GamePlayer GP){
        for(int i=0; i<4; i++){
            if(cardsPlayed[i] == null){
                cardsPlayed[i]=card;
                if(i == 0){
                    SuitIndex = card.getSuit();
                }
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
