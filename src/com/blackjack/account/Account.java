package com.blackjack.account;

public class Account {

	private double balance;
	
	public Account(double startingBalance) {
		this.balance = startingBalance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		}
		balance = balance + amount;
		return true;
	}
	
	public boolean withdraw(double amount) {
		if (amount <= 0 || amount > balance) {
			return false;
		}
		balance = balance - amount;
		return true;
	}
	
	public boolean hasSufficientFunds(double amount) {
		return balance >= amount;
	}
}
