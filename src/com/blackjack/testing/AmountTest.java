package com.blackjack.testing;

import org.junit.Test;
import static org.junit.Assert.*;
import com.blackjack.account.Amount;

public class AmountTest {

    @Test
    public void testValidAmountIsValid() {
        Amount a = new Amount(50.0); // create amount of 50
        assertTrue(a.isValid()); // 50 should be valid
    }

    @Test
    public void testZeroAmountIsNotValid() {
        Amount a = new Amount(0); // create amount of 0
        assertFalse(a.isValid()); // 0 should not be valid
    }

    @Test
    public void testNegativeAmountBecomesZero() {
        Amount a = new Amount(-20.0); // create negative amount
        assertEquals(0.0, a.getValue(), 0.001); // should be stored as 0
    }

    @Test
    public void testGetValueReturnsCorrectly() {
        Amount a = new Amount(75.0); // create amount of 75
        assertEquals(75.0, a.getValue(), 0.001); // value should be 75
    }

    @Test
    public void testToStringFormat() {
        Amount a = new Amount(25.0); // create amount of 25
        assertEquals("$25.0", a.toString()); // should display as $25.0
    }

    @Test
    public void testLargeAmountIsValid() {
        Amount a = new Amount(10000.0); // create large amount
        assertTrue(a.isValid()); // large amount should still be valid
    }
}