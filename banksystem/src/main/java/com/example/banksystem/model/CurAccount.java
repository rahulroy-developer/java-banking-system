package com.example.banksystem.model;

public class CurAccount extends Account {
    private double minBalance = 1000;
    private double penalty = 200;

    public CurAccount(String customerName, int accountNumber, double balance) {
        super(customerName, accountNumber, "Current", balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            checkMinimumBalance();
        }
    }

    private void checkMinimumBalance() {
        if (balance < minBalance) {
            balance -= penalty;
        }
    }
}