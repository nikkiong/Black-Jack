package com.blackjack.game;

// enum to represent the different states a table 
public enum TableStatus {
    WAITING,   // table is waiting for players to join
    ACTIVE,    // game is currently in progress
    FULL,      // table has reached max player capacity
    EMPTY      // no players at the table
}

