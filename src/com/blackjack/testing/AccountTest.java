package com.blackjack.testing;

import org.junit.Test;
import static org.junit.Assert.*;
import com.blackjack.account.Account;

public class AccountTest {

    @Test
    public void testStartingBalanceIsSet() {
        Account acc = new Account(100.0); // create account with 100
        assertEquals(100.0, acc.getBalance(), 0.001); // balance should be 100
    }

    @Test
    public void testDepositIncreasesBalance() {
        Account acc = new Account(100.0); // start with 100
        acc.deposit(50.0); // deposit 50
        assertEquals(150.0, acc.getBalance(), 0.001); // balance should be 150
    }

    @Test
    public void testDepositNegativeAmountFails() {
        Account acc = new Account(100.0); // create account
        boolean result = acc.deposit(-10.0); // try to deposit negative amount
        assertFalse(result); // should fail
    }

    @Test
    public void testDepositZeroFails() {
        Account acc = new Account(100.0); // create account
        boolean result = acc.deposit(0); // try to deposit zero
        assertFalse(result); // should fail
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        Account acc = new Account(100.0); // start with 100
        acc.withdraw(40.0); // withdraw 40
        assertEquals(60.0, acc.getBalance(), 0.001); // balance should be 60
    }

    @Test
    public void testWithdrawMoreThanBalanceFails() {
        Account acc = new Account(50.0); // start with 50
        boolean result = acc.withdraw(100.0); // try to withdraw 100
        assertFalse(result); // should fail, not enough funds
    }

    @Test
    public void testBalanceNeverGoesBelowZero() {
        Account acc = new Account(50.0); // start with 50
        acc.withdraw(100.0); // try to overdraw
        assertTrue(acc.getBalance() >= 0); // balance should never be negative
    }

    @Test
    public void testHasSufficientFundsTrue() {
        Account acc = new Account(100.0); // start with 100
        assertTrue(acc.hasSufficientFunds(100.0)); // 100 should be enough for 100
    }

    @Test
    public void testHasSufficientFundsFalse() {
        Account acc = new Account(30.0); // start with 30
        assertFalse(acc.hasSufficientFunds(50.0)); // 30 is not enough for 50
    }
}



