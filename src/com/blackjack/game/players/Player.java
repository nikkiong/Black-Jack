package com.blackjack.game.players;

import com.blackjack.account.Amount;
import com.blackjack.account.User;
import com.blackjack.game.card_system.Hand;

public class Player extends User {

    private Hand hand;
    private double currentBet;
    private boolean folded;
    private boolean standing;

    public Player(String username, String password, double startingBalance) {
        super(username, password, startingBalance);
        this.hand = new Hand();
        this.currentBet = 0;
        this.folded = false;
        this.standing = false;
    }

    public boolean placeBet(double amount) {
        if (!hasSufficientFunds(amount) || amount <= 0) {
            return false;
        }
        Amount betAmount = new Amount(amount);
        boolean success = withdraw(betAmount);
        if (success) {
            currentBet = amount;
        }
        return success;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isFolded() {
        return folded;
    }

    public boolean isStanding() {
        return standing;
    }

    public void fold() {
        folded = true;
    }

    public void stand() {
        standing = true;
    }

    public void resetRound() {
        hand.clear();
        currentBet = 0;
        folded = false;
        standing = false;
    }
}