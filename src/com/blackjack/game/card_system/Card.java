package com.blackjack.game.card_system;

public class Card {

    private String rank; // cards rank ex: Ace, King
    
    private String suit;// cards suit ex: Hearts
    
    private int value;// cards number value
    
    private boolean faceUp;// is card visible

    // default constructor
    public Card() {
        this.rank = "";
        this.suit = "";
        this.value = 0;
        this.faceUp = true;
    }

    public Card(String rank, String suit, int value, boolean faceUp) {// constructor with all values
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.faceUp = faceUp;
    }

    public String getRank() {// returns the rank
        return rank;
    }

    public String getSuit() {// returns the suit
        return suit;
    }

    public int getValue() {// returns the value
        return value;
    }
    
    public void setValue(int value) {// sets the value
        this.value = value;
    }

    public boolean isAce() {// checks if ace
        return rank.equals("Ace");
    }

    
    public void flip() {// flips card visibility
        faceUp = !faceUp;
    }

    public boolean isFaceUp() {// checks if face up
        return faceUp;
    }
}