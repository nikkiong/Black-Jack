package com.blackjack.game.card_system;

public class Card {

    private String rank;    // cards rank ex: Ace, King
    private String suit;    // cards suit ex: Hearts
    private int value;      // cards number value
    private boolean faceUp; // is card visible

    // default constructor
    public Card() {
        this.rank = "";
        this.suit = "";
        this.value = 0;
        this.faceUp = false; // hidden by default
    }

    // constructor with all values
    public Card(String rank, String suit, int value, boolean faceUp) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.faceUp = faceUp;
    }

    // returns the rank
    public String getRank() {
        return rank;
    }

    // returns the suit
    public String getSuit() {
        return suit;
    }

    // returns the value
    public int getValue() {
        return value;
    }

    // sets the value
    public void setValue(int value) {
        this.value = value;
    }

    // checks if ace
    public boolean isAce() {
        if (rank == null) {       // null check
            return false;
        }
        return rank.toUpperCase().equals("ACE"); // case insensitive check
    }

    // flips card visibility
    public void flip() {
        faceUp = !faceUp;
    }

    // checks if face up
    public boolean isFaceUp() {
        return faceUp;
    }
}