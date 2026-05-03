package com.blackjack.account;

public class Amount {

    private double value;

    public Amount(double value) {
        if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
    }

    public double getValue() {
        return value;
    }

    public boolean isValid() {
        return value > 0;
    }

    public String toString() {
        return "$" + value;
    }
}