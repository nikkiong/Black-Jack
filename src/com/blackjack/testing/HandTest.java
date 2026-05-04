package com.blackjack.testing;

import com.blackjack.game.card_system.Hand;
import com.blackjack.game.card_system.Card;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void testHandStartsEmpty() {
        Hand hand = new Hand(); // create new hand
        assertEquals(0, hand.getCards().size());// should have no cards
    }

    @Test
    public void testAddCard() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("7", "Hearts", 7, true));// add a card
        assertEquals(1, hand.getCards().size());// should have 1 card
    }

    @Test
    public void testGetTotal() {
        Hand hand = new Hand(); //create new hand
        hand.addCard(new Card("7", "Hearts", 7, true));// add 7
        hand.addCard(new Card("8", "Spades", 8, true));//add 8
        assertEquals(15, hand.getTotal());//total should be 15
    }

    @Test
    public void testAceDropsTo1() {
        Hand hand = new Hand();//create new hand
        hand.addCard(new Card("Ace", "Hearts", 11, true));//add ace as 11
        hand.addCard(new Card("King", "Spades", 10, true));// add king as 10
        hand.addCard(new Card("5", "Diamonds", 5, true));// add 5
        assertEquals(16, hand.getTotal());// ace drops to 1
    }

    @Test
    public void testIsBustTrue() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("King", "Hearts", 10, true)); // add 10
        hand.addCard(new Card("Queen", "Spades", 10, true));// add 10
        hand.addCard(new Card("5", "Diamonds", 5, true));// add 5
        assertTrue(hand.isBust());// total is 25 bust
    }

    @Test
    public void testIsBustFalse() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("King", "Hearts", 10, true)); // add 10
        hand.addCard(new Card("7", "Spades", 7, true));// add 7
        assertFalse(hand.isBust());// total is 17 safe
    }

    @Test
    public void testIsBlackjackTrue() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("Ace", "Hearts", 11, true));// add ace as 11
        hand.addCard(new Card("King", "Spades", 10, true));// add king as 10
        assertTrue(hand.isBlackjack());// total is 21
    }

    @Test
    public void testIsBlackjackFalse() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("Ace", "Hearts", 11, true));// add ace
        hand.addCard(new Card("5", "Spades", 5, true));// add 5
        hand.addCard(new Card("5", "Diamonds", 5, true));// add another 5
        assertFalse(hand.isBlackjack());// not blackjack
    }

    @Test
    public void testClear() {
        Hand hand = new Hand();// create new hand
        hand.addCard(new Card("7", "Hearts", 7, true));// add a card
        hand.addCard(new Card("8", "Spades", 8, true));// add another card
        hand.clear();// clear the hand
        assertEquals(0, hand.getCards().size());// should be empty
    }
}