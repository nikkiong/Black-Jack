package com.blackjack.testing;

import com.blackjack.game.card_system.Deck;
import com.blackjack.game.card_system.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testDeckSize() {
        Deck deck = new Deck();// create new deck
        assertEquals(52, deck.remainingCards()); // should have 52 cards
    }

    @Test
    public void testShuffle() {
        Deck deck = new Deck();// create new deck
        deck.shuffle();// shuffle the deck
        assertEquals(52, deck.remainingCards()); // still 52 cards
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();// create new deck
        Card card = deck.drawCard();// draw a card
        assertNotNull(card);// card should not be null
        assertEquals(51, deck.remainingCards()); // 51 cards left
    }

    @Test
    public void testRemainingCards() {
        Deck deck = new Deck();// create new deck
        deck.drawCard();// draw one card
        deck.drawCard();// draw another card
        assertEquals(50, deck.remainingCards()); // 50 cards left
    }

    @Test
    public void testIsEmptyFalse() {
        Deck deck = new Deck();// create new deck
        assertFalse(deck.isEmpty());// should not be empty
    }

    @Test
    public void testIsEmptyTrue() {
        Deck deck = new Deck();// create new deck
        for (int i = 0; i < 52; i++) {// draw all 52 cards
            deck.drawCard();// draw each card
        }
        assertTrue(deck.isEmpty());// deck should be empty
    }

    @Test
    public void testResetDeck() {
        Deck deck = new Deck();// create new deck
        deck.drawCard();// draw a card
        deck.drawCard(); // draw another card
        deck.resetDeck(); // reset the deck
        assertEquals(52, deck.remainingCards()); // back to 52
    }

    @Test
    public void testDrawReturnsNullWhenEmpty() {
        Deck deck = new Deck(); // create new deck
        for (int i = 0; i < 52; i++) {// draw all cards
            deck.drawCard();// draw each card
        }
        assertNull(deck.drawCard());// should return null
    }
}