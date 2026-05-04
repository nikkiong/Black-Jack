package com.blackjack.testing;

import org.junit.Test;
import static org.junit.Assert.*;
import com.blackjack.game.Table;
import com.blackjack.game.TableStatus;
import com.blackjack.game.players.Player;
import com.blackjack.game.players.Dealer;

public class TableTest {

    @Test
    public void testTableStartsEmpty() {
        Table table = new Table(1); // create table with id 1
        assertEquals(0, table.getPlayerCount()); // should have no players
    }

    @Test
    public void testTableStatusIsEmptyOnStart() {
        Table table = new Table(1); // create new table
        assertEquals(TableStatus.EMPTY, table.getStatus()); // status should be empty
    }

    @Test
    public void testAddPlayerIncreasesCount() {
        Table table = new Table(1); // create table
        Player p = new Player("daniel", "pass123", 100.0); // create player
        table.addPlayer(p); // add player to table
        assertEquals(1, table.getPlayerCount()); // should have 1 player
    }

    @Test
    public void testAddPlayerUpdatesStatusToWaiting() {
        Table table = new Table(1); // create table
        Player p = new Player("daniel", "pass123", 100.0); // create player
        table.addPlayer(p); // add player
        assertEquals(TableStatus.WAITING, table.getStatus()); // status should be waiting
    }

    @Test
    public void testTableIsFullAt6Players() {
        Table table = new Table(1); // create table
        // add 6 players to fill the table
        for (int i = 0; i < 6; i++) {
            table.addPlayer(new Player("player" + i, "pass", 100.0));
        }
        assertTrue(table.isFull()); // table should be full
    }

    @Test
    public void testTableStatusIsFullWhenFull() {
        Table table = new Table(1); // create table
        // add 6 players
        for (int i = 0; i < 6; i++) {
            table.addPlayer(new Player("player" + i, "pass", 100.0));
        }
        assertEquals(TableStatus.FULL, table.getStatus()); // status should be full
    }

    @Test
    public void testAddPlayerFailsWhenFull() {
        Table table = new Table(1); // create table
        // fill the table
        for (int i = 0; i < 6; i++) {
            table.addPlayer(new Player("player" + i, "pass", 100.0));
        }
        Player extra = new Player("extra", "pass", 100.0); // one more player
        boolean result = table.addPlayer(extra); // try to add 7th player
        assertFalse(result); // should fail, table is full
    }

    @Test
    public void testRemovePlayerDecreasesCount() {
        Table table = new Table(1); // create table
        Player p = new Player("daniel", "pass123", 100.0); // create player
        table.addPlayer(p); // add player
        table.removePlayer("daniel"); // remove player
        assertEquals(0, table.getPlayerCount()); // should have 0 players
    }

    @Test
    public void testRemovePlayerNotFoundReturnsFalse() {
        Table table = new Table(1); // create table
        boolean result = table.removePlayer("nobody"); // try to remove non-existent player
        assertFalse(result); // should return false
    }

    @Test
    public void testTableIsEmptyAfterAllPlayersLeave() {
        Table table = new Table(1); // create table
        Player p = new Player("daniel", "pass123", 100.0); // create player
        table.addPlayer(p); // add player
        table.removePlayer("daniel"); // remove player
        assertTrue(table.isEmpty()); // table should be empty again
    }

    @Test
    public void testSetAndGetDealer() {
        Table table = new Table(1); // create table
        Dealer d = new Dealer("dealer1", "pass123"); // create dealer
        table.setDealer(d); // assign dealer to table
        assertEquals(d, table.getDealer()); // dealer should match
    }

    @Test
    public void testHasDealerFalseByDefault() {
        Table table = new Table(1); // create table
        assertFalse(table.hasDealer()); // no dealer assigned yet
    }

    @Test
    public void testHasDealerTrueAfterSet() {
        Table table = new Table(1); // create table
        Dealer d = new Dealer("dealer1", "pass123"); // create dealer
        table.setDealer(d); // assign dealer
        assertTrue(table.hasDealer()); // should now have a dealer
    }

    @Test
    public void testResetTableClearsPlayerHands() {
        Table table = new Table(1); // create table
        Player p = new Player("daniel", "pass123", 100.0); // create player
        table.addPlayer(p); // add player
        table.resetTable(); // reset the table
        assertEquals(0, p.getHand().size()); // player hand should be empty
    }

    @Test
    public void testGetMaxPlayers() {
        Table table = new Table(1); // create table
        assertEquals(6, table.getMaxPlayers()); // max should be 6
    }

    @Test
    public void testGetTableId() {
        Table table = new Table(5); // create table with id 5
        assertEquals(5, table.getTableId()); // id should be 5
    }
}


