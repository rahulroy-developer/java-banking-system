package com.example.banksystem.model;

public class SavAccount extends Account {
    private double interestRate = 0.05;

    public SavAccount(String customerName, int accountNumber, double balance) {
        super(customerName, accountNumber, "Savings", balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }
}