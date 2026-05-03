package com.blackjack.game;

public enum RoundPhase {

    BETTING,        // players place bets
    PLAYER_TURNS,   // players take turns
    DEALER_TURN,    // dealer plays hand
    PAYOUT,         // winners get paid
    ROUND_END       // round is finished
}