package com.blackjack.game;

import com.blackjack.account.Amount;
import com.blackjack.game.players.Player;
import com.blackjack.game.players.Dealer;
import com.blackjack.game.card_system.Deck;
import com.blackjack.game.card_system.Card;
import java.util.List;

public class GameManagement {

    private RoundPhase currentPhase;      // current round phase
    private int currentPlayerIndex;       // whose turn it is
    private Amount totalBets;             // total bets placed
    private int turnTimeLimit;            // time limit per turn

    // default constructor
    public GameManagement() {
        this.currentPhase = RoundPhase.BETTING; // start at betting
        this.currentPlayerIndex = 0;            // first player starts
        this.totalBets = new Amount(0);         // no bets yet
        this.turnTimeLimit = 30;                // default 30 seconds
    }

    // constructor with time limit
    public GameManagement(int turnTimeLimit) {
        this.currentPhase = RoundPhase.BETTING; // start at betting
        this.currentPlayerIndex = 0;            // first player starts
        this.totalBets = new Amount(0);         // no bets yet
        this.turnTimeLimit = turnTimeLimit;     // custom time limit
    }

    // checks if round can start
    public boolean validateRoundStart(List<Player> players) {
        if (players == null || players.isEmpty()) { // no players check
            return false;
        }
        return currentPhase == RoundPhase.BETTING; // must be betting phase
    }

    // confirms all bets placed
    public boolean confirmBets(List<Player> players) {
        for (Player player : players) {         // loop through players
            if (player.getCurrentBet() <= 0) { // check bet is placed
                return false;                  // bet missing
            }
        }
        currentPhase = RoundPhase.PLAYER_TURNS; // move to player turns
        return true;                            // all bets confirmed
    }

    // deals cards to everyone
    public void dealInitialCards(List<Player> players, Dealer dealer, Deck deck) {
        for (Player player : players) {               // deal to each player
            player.getHand().addCard(deck.drawCard()); // first card
            player.getHand().addCard(deck.drawCard()); // second card
        }
        dealer.getHand().addCard(deck.drawCard());     // dealer first card
        dealer.getHand().addCard(deck.drawCard());     // dealer second card
    }

    // applies hit stand or fold
    public void applyPlayerAction(int playerId, PlayerAction action, List<Player> players, Deck deck) {
        Player player = players.get(playerId); // get current player

        if (action == PlayerAction.HIT) {               // player hits
            Card card = deck.drawCard();                // draw a card
            player.getHand().addCard(card);             // add to hand
            if (player.getHand().isBust()) {            // check if bust
                advanceTurn(players);                   // move to next
            }
        } else if (action == PlayerAction.STAND) {      // player stands
            player.stand();                             // mark standing
            advanceTurn(players);                       // move to next
        } else if (action == PlayerAction.FOLD) {       // player folds
            player.fold();                              // mark folded
            advanceTurn(players);                       // move to next
        }
    }

    // moves to next player
    public void advanceTurn(List<Player> players) {
        currentPlayerIndex++;                              // next player
        if (currentPlayerIndex >= players.size()) {        // all done check
            currentPhase = RoundPhase.DEALER_TURN;         // dealer's turn
        }
    }

    // checks if all players done
    public boolean allPlayersFinished(List<Player> players) {
        for (Player player : players) {                    // loop players
            if (!player.isStanding() && !player.isFolded() // not done yet
                && !player.getHand().isBust()) {
                return false;                              // someone still playing
            }
        }
        return true;                                       // all finished
    }

    // runs the dealer turn
    public void runDealerTurn(Dealer dealer, Deck deck) {
        currentPhase = RoundPhase.DEALER_TURN;             // set dealer phase
        while (dealer.shouldHit()) {                       // dealer hits below 17
            dealer.getHand().addCard(deck.drawCard());     // draw card
        }
        currentPhase = RoundPhase.PAYOUT;                  // move to payout
    }

    // resolves the round
    public void resolveRound(List<Player> players, Dealer dealer) {
        int dealerTotal = dealer.getHand().getTotal();     // get dealer total
        for (Player player : players) {                    // loop players
            if (player.isFolded()) continue;               // skip folded
            int playerTotal = player.getHand().getTotal(); // get player total
            if (!player.getHand().isBust() &&              // player not bust
               (dealer.getHand().isBust() ||               // dealer bust
                playerTotal > dealerTotal)) {              // player wins
                double winnings = player.getCurrentBet() * 2; // double bet
                player.deposit(new Amount(winnings));          // pay player
            }
        }
        currentPhase = RoundPhase.ROUND_END;               // round finished
    }

    // resets for next round
    public void resetRound(List<Player> players, Dealer dealer, Deck deck) {
        for (Player player : players) { // reset each player
            player.resetRound();        // clear hand and bet
        }
        dealer.resetRound();            // clear dealer hand
        deck.shuffle();                 // reshuffle deck
        currentPlayerIndex = 0;         // back to first player
        currentPhase = RoundPhase.BETTING; // back to betting
    }

    // returns current phase
    public RoundPhase getCurrentPhase() {
        return currentPhase;
    }

    // returns time limit
    public int getTurnTimeLimit() {
        return turnTimeLimit;
    }
}