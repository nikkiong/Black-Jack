package com.blackjack.game.players;

import com.blackjack.account.User;
import com.blackjack.game.card_system.Hand;

public class Dealer extends User {
    private Hand hand;

    public Dealer(String username, String password) {
        super(username, password, 0);
        hand = new Hand();
    }

    public Hand getHand() { return hand; }

    public boolean shouldHit() {
        return hand.getTotal() < 17; // dealer must hit below 17
    }

    public void resetRound() { hand.clear(); }
}