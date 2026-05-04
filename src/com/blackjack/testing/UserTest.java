package com.blackjack.testing;

import org.junit.Test;
import static org.junit.Assert.*;
import com.blackjack.account.User;
import com.blackjack.account.Amount;

public class UserTest {

    @Test
    public void testGetUsernameReturnsCorrectly() {
        User u = new User("monisha", "pass123", 100.0); // create user
        assertEquals("monisha", u.getUsername()); // username should be monisha
    }

    @Test
    public void testCheckPasswordCorrect() {
        User u = new User("monisha", "mypassword", 0); // create user
        assertTrue(u.checkPassword("mypassword")); // correct password should return true
    }

    @Test
    public void testCheckPasswordWrong() {
        User u = new User("monisha", "mypassword", 0); // create user
        assertFalse(u.checkPassword("wrongpassword")); // wrong password should return false
    }

    @Test
    public void testDepositIncreasesBalance() {
        User u = new User("monisha", "pass123", 100.0); // start with 100
        u.deposit(new Amount(50.0)); // deposit 50
        assertEquals(150.0, u.getBalance(), 0.001); // balance should be 150
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        User u = new User("monisha", "pass123", 100.0); // start with 100
        u.withdraw(new Amount(40.0)); // withdraw 40
        assertEquals(60.0, u.getBalance(), 0.001); // balance should be 60
    }

    @Test
    public void testWithdrawMoreThanBalanceFails() {
        User u = new User("monisha", "pass123", 50.0); // start with 50
        boolean result = u.withdraw(new Amount(200.0)); // try to withdraw 200
        assertFalse(result); // should fail, not enough funds
    }

    @Test
    public void testHasSufficientFundsTrue() {
        User u = new User("monisha", "pass123", 100.0); // start with 100
        assertTrue(u.hasSufficientFunds(100.0)); // 100 should be enough for 100
    }

    @Test
    public void testHasSufficientFundsFalse() {
        User u = new User("monisha", "pass123", 30.0); // start with 30
        assertFalse(u.hasSufficientFunds(50.0)); // 30 is not enough for 50
    }

    @Test
    public void testStartingBalanceIsSet() {
        User u = new User("monisha", "pass123", 200.0); // create user with 200
        assertEquals(200.0, u.getBalance(), 0.001); // balance should be 200
    }
}





