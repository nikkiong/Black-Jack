package com.blackjack.game;

import com.blackjack.game.players.Player;
import com.blackjack.game.players.Dealer;
import com.blackjack.game.card_system.Deck;

import java.util.ArrayList;

public class Table {

    private int tableId;
    private ArrayList<Player> players;
    private Dealer dealer;
    private Deck deck;
    private TableStatus status;
    private static final int MAX_PLAYERS = 6; // max 6 players per table

    // constructor creates new empty table with given id
    public Table(int tableId) {
        this.tableId = tableId;
        this.players = new ArrayList<Player>();
        this.dealer = null;
        this.deck = new Deck();
        this.status = TableStatus.EMPTY;
    }

    // returns the table id
    public int getTableId() {
        return tableId;
    }

    // returns the list of players at this table
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // returns the dealer at this table
    public Dealer getDealer() {
        return dealer;
    }

    // sets the dealer for this table
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    // returns the deck being used at this table
    public Deck getDeck() {
        return deck;
    }

    // returns how many players are currently at the table
    public int getPlayerCount() {
        return players.size();
    }

    // returns true if table has reached max capacity
    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    // returns true if no players are at the table
    public boolean isEmpty() {
        return players.size() == 0;
    }

    // returns true if table has a dealer assigned
    public boolean hasDealer() {
        return dealer != null;
    }

    // adds a player to the table if there is space
    public boolean addPlayer(Player player) {
        if (isFull()) {
            return false; // table is full, cannot add
        }
        players.add(player);
        updateStatus(); // update status after adding
        return true;
    }

    // removes a player from the table by username
    public boolean removePlayer(String username) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUsername().equals(username)) {
                players.remove(i); // remove matching player
                updateStatus(); // update status after removing
                return true;
            }
        }
        return false; // player not found
    }

    // returns the current status of the table
    public TableStatus getStatus() {
        return status;
    }

    // manually set the table status
    public void setStatus(TableStatus status) {
        this.status = status;
    }

    // updates status automatically based on current state
    public void updateStatus() {
        if (players.size() == 0) {
            status = TableStatus.EMPTY; // no players
        } else if (isFull()) {
            status = TableStatus.FULL; // max players reached
        } else {
            status = TableStatus.WAITING; // has players but not full
        }
    }

    // resets the table for a new round
    public void resetTable() {
        deck = new Deck(); // get a fresh deck
        deck.shuffle(); // shuffle it

        // reset each player's hand and bet
        for (int i = 0; i < players.size(); i++) {
            players.get(i).resetRound();
        }

        // reset dealer hand if dealer is assigned
        if (dealer != null) {
            dealer.resetRound();
        }
    }

    // returns max number of players allowed
    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    // returns a simple string description of the table
    public String toString() {
        return "Table " + tableId + " | Players: " + players.size() + "/" + MAX_PLAYERS + " | Status: " + status;
    }
}





