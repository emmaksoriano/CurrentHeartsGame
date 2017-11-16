package edu.up.cs301.hearts;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by emmasoriano on 10/19/17.
 */

public class CardDeck {

    Card[] deck = new Card[52];
    public String[] suitValues = {"Clubs", "Spades", "Diamonds", "Hearts"};

    // Constructor
    public CardDeck(){
        //initialize the deck so the cards are "in order"

        int i, j;
        int index = 0;
        for (i = 0; i < 4; i++){
            for(j = 2; j < 13; j++){
                deck[index] = new Card(j, i);
                index++;
            }
        }
        shuffle();
    }

    /**
     * Shuffles the deck
     */
    public void shuffle(){
        int i, ranNum;
        ArrayList<Card> tempList = new ArrayList<>(Arrays.asList(deck));
        Card[] tempDeck = new Card[52];

        for (i = 0; i < 52; i++){
            ranNum =(int)(Math.random()*(tempList.size()-1));
            tempDeck[i] = tempList.get(ranNum);
            tempList.remove(ranNum);
        }
        deck = tempDeck;
    }

    /**
     *
     * @return Card[4][13]
     * The four players then their hands of 13 cards
     */
    public Card[][] dealHand() {
        shuffle();
        Card[][] hand = new Card[4][13];

        int counter = 0;
        int i, j;

        for (i = 0; i < 4; i++) {
            for (j = 0; j < hand[0].length; j++) {
                hand[i][j] = deck[counter];
                counter++;
            }
            sortCards(hand[i]);
        }
        //takes 13 cards from top of deck, removes them from deck and adds them to players hand


        return hand;
    }

    /**
     * sorts a given set of cards by suit and value
     * @param cards
     * @return
     */
    public Card[] sortCards(Card[] cards){
        // sorts cards based on suit and
        int i, j;
        int indexCounter = 0;
        int count = 0;
        Card[] rtrnHand = new Card[cards.length];
        ArrayList<Card> cardsToSort = new ArrayList<>();

        if(cards != null) {
            for (i = 0; i < 4; i++) {
                cardsToSort = new ArrayList<>();
                for (j = 0; j < cards.length - 1; j++) {
                    if(cards[i] != null) {
                        if (cards[j].suitValueIndex == i) {
                            cardsToSort.add(cards[j]);
                            count++;
                        }
                    }
                }
                insertionSort(cardsToSort);
                for(Card C : cardsToSort){
                    rtrnHand[indexCounter] = C;
                    indexCounter++;
                }
                if(indexCounter >= cards.length){
                    break;
                }
            }
        }
        return rtrnHand;
    }

    /**
     * Uses the insertion sort algorithm to sort cards based on face value
     * @param cards
     * @return
     */
    public ArrayList<Card> insertionSort(ArrayList<Card> cards){

        int i, j;
        Card tempCard;

        for(i = 1; i < cards.size(); i++){
            tempCard = cards.get(i);
            j = i - 1;
            while ((j >= 0) && (cards.get(j).faceValue > tempCard.faceValue)){
                cards.set(j+1, cards.get(j));
                j = j-1;
            }
            cards.set(j+1, tempCard);
        }

        return cards;
    }

    /**
     * Uses a quick sort algorithm to sort cards
     * based on face value, not suit.
     * @param cards
     * @return
     */
    public ArrayList<Card> quickSort(ArrayList<Card> cards){

        // Declare Variables
        ArrayList<Card> rtnCards = new ArrayList<>();
        ArrayList<Card> greater = new ArrayList<>();
        ArrayList<Card> less = new ArrayList<>();
        Card pivotCard;
        int pIndex;

        if(cards != null){
            if(cards.size() == 1){
                return cards;
            }
            else{

                pIndex = cards.size()/2;
                pivotCard = cards.get(pIndex);
                for(Card c : cards){
                    if(!(c.equals(pivotCard))){
                        if(c.faceValue > pivotCard.faceValue){
                            greater.add(c);
                        }
                        else{
                            less.add(c);
                        }
                    }
                }

                rtnCards.addAll(quickSort(greater));
                rtnCards.add(pivotCard);
                rtnCards.addAll(quickSort(less));
            }
        }
        return rtnCards;
    }
}
