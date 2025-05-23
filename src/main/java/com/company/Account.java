package com.company;

public class Account {

    private long availableAmount;

    public Account() {
    }

    public long getBalance() {
        return availableAmount;
    }

    public void addAmount(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        availableAmount += amount;
    }

    public void deductAmount(long amount) {
        if ((availableAmount - amount) < 0) {
            throw new IllegalArgumentException("Balance is too low. availableAmount: " + availableAmount + ", amount: " + amount);
        }
        availableAmount -= amount;
    }

    public void reset() {
        // This can potentially fire an event to let the vending machine push out the change
        availableAmount = 0;
    }
}
