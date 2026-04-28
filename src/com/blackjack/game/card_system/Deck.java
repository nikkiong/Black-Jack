package com.blackjack.game.card_system;

import java.util.*;

public class Deck {

    private List<Card> cards;// holds all cards
    
    private int nextCardIndex; // tracks next card

    public Deck() { // builds 52 cards
        cards = new ArrayList<>();
        nextCardIndex = 0;
        
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};// all four suits
        
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};// all thirteen ranks
        
        // loop through suits
        for (String suit : suits) {
            // loop through ranks
            for (String rank : ranks) {
                
                int value;// set card value
                
                if (rank.equals("Ace")) { // ace is 11
                    value = 11;
                    
                } else if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {// face cards are 10
                    value = 10;
                    
                } else {
                    value = Integer.parseInt(rank);// number cards face value
                }
                
                cards.add(new Card(rank, suit, value, true));// add card to deck
            }
        }
    }

    // shuffles the deck
    public void shuffle() {
        Collections.shuffle(cards);
        nextCardIndex = 0;
    }

    // draws next card
    public Card drawCard() {
        // check if empty
        if (isEmpty()) {
            return null;
        }
        return cards.get(nextCardIndex++);// return next card
    }

    public void resetDeck() {// resets the deck
        nextCardIndex = 0;
    }

    public boolean isEmpty() {// checks if empty
        return nextCardIndex >= cards.size();
    }

    public int remainingCards() {// returns cards left
        return cards.size() - nextCardIndex;
    }
}