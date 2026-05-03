package com.blackjack.testing;

import com.blackjack.game.card_system.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testConstructor() {
        Card card = new Card("Ace", "Hearts", 11, true); // create test card
        assertEquals("Ace", card.getRank());// check rank is Ace
        assertEquals("Hearts", card.getSuit());// check suit is Hearts
        assertEquals(11, card.getValue());// check value is 11
        assertTrue(card.isFaceUp());// check card is face up
    }

    @Test
    public void testGetRank() {
        Card card = new Card("King", "Spades", 10, true);// create king card
        assertEquals("King", card.getRank());// rank should be King
    }

    @Test
    public void testGetSuit() {
        Card card = new Card("King", "Spades", 10, true);// create spades card
        assertEquals("Spades", card.getSuit());// suit should be Spades
    }

    @Test
    public void testGetValue() {
        Card card = new Card("7", "Diamonds", 7, true); // create 7 card
        assertEquals(7, card.getValue());// value should be 7
    }

    @Test
    public void testIsAce() {
        Card ace = new Card("Ace", "Hearts", 11, true);  // create ace card
        Card king = new Card("King", "Spades", 10, true); // create king card
        assertTrue(ace.isAce());// ace should be true
        assertFalse(king.isAce());// king should be false
    }

    @Test
    public void testFlip() {
        Card card = new Card("5", "Clubs", 5, true); // card starts face up
        card.flip();// flip the card
        assertFalse(card.isFaceUp());// should now be face down
        card.flip();// flip again
        assertTrue(card.isFaceUp());// should be face up again
    }

    @Test
    public void testSetValue() {
        Card ace = new Card("Ace", "Hearts", 11, true); // ace starts at 11
        ace.setValue(1);// change value to 1
        assertEquals(1, ace.getValue());// value should be 1
    }
}