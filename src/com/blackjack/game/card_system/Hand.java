package com.blackjack.game.card_system;

import java.util.*;

public class Hand {

    private List<Card> cards;// holds all cards

    public Hand() { // empty hand
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {// adds card to hand
        cards.add(card);
    }

    public void clear() { // clears the hand
        cards.clear();
    }
    
    public List<Card> getCards() {// returns all cards
        return cards;
    }

    public int size() { // returns number of cards in hand
        return cards.size();
    }

    public int getTotal() {// calculates hand total
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {// add up all cards
            total += card.getValue();

            if (card.isAce()) {// count the aces
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) { // ace drops to 1 if bust
            total -= 10;
            aceCount--;
        }

        return total;
    }

    // checks if over 21
    public boolean isBust() {
        return getTotal() > 21;
    }

    // checks if blackjack
    public boolean isBlackjack() {
        // exactly 2 cards
        // and total is 21
        return cards.size() == 2 && getTotal() == 21;
    }
}