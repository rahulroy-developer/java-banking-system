package com.example.banksystem.model;

public abstract class Account {
    protected String customerName;
    protected int accountNumber;
    protected String accountType;
    protected double balance;

    public Account(String customerName, int accountNumber, String accountType, double balance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void withdraw(double amount);

    // Getters are REQUIRED for the web to read your data
    public double getBalance() { return balance; }
    public int getAccountNumber() { return accountNumber; }
    public String getCustomerName() { return customerName; }
    public String getAccountType() { return accountType; }
}