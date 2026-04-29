package com.blackjack.game.card_system;

// Represents a single playing card
public class Card {
	
	private String rank;    // rank of a card (ex: "Jack", "Ace", "2")
	private String suit;	// suits of a card (ex: 
	private int value;		// In game Blackjack value of the card (ex: 2-10 for number cards, king/queen/jack = 10, Ace = 11 initially)
	private boolean faceUp; // represents if card is faced up/true/visible or faced down/hidden/false
	
	/***************************************************************
	 * 						CONSTRUCTORS
	 ***************************************************************/
	// Default constructor that creates an "empty" card
	public Card()
	{
		// initializes with default placeholder values
		rank = "";
		suit = "";
		value = 0;
		faceUp = false; // initializes as hidden by default
	}
	
	// Main Constructor to create the cards
	public Card (String rank, String suit, int value, boolean faceUp)
	{
		// Assigns card values (ex: "Ace", "Hearts", 11, true (visible/faced up)
		this.rank = rank;
		this.suit = suit;
		this.value = value;
		this.faceUp = faceUp;
	}
	
	// Getter Method that returns the numeric value of this card
	public int getValue()
	{
		return value; // used for calculating the hand total
	}
	
	// Method that checks if this card is an Ace
	// + returns true if it is an Ace, false if not
	public boolean isAce()
	{
		// if rank hasn't been assigned, meaning it cant be compared
		if(rank == null)
		{
			return false;
		}
		
		// returns false or true after converting to uppercase and compare it to "ACE
		return rank.toUpperCase().equals("ACE");
	}
	
	// Method that flips the card to make it visible or hidden
	public void flip()
	{
		// changes the current assigned boolean to the opposite value
		// (true = false <-> false = true) ; (visible = hidden <-> hidden = visible)
		faceUp = !faceUp;
	}
}
