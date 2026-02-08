package com.example.banksystem.service;

import com.example.banksystem.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    // List to store accounts in memory
    private List<Account> accounts = new ArrayList<>();

    // 1. Create Account
    public String createAccount(String name, int accNo, double balance, String type) {
        if (findAccount(accNo) != null) return "Account already exists!";

        if (type.equalsIgnoreCase("savings")) {
            accounts.add(new SavAccount(name, accNo, balance));
            return "Savings Account Created Successfully!";
        } else if (type.equalsIgnoreCase("current")) {
            accounts.add(new CurAccount(name, accNo, balance));
            return "Current Account Created Successfully!";
        }
        return "Invalid Account Type";
    }

    // Helper Method to find account by ID
    public Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    // 2. Deposit
    public String deposit(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc == null) return "Account not found";

        acc.deposit(amount);
        return "Success: Deposited $" + amount + ". New Balance: $" + acc.getBalance();
    }

    // 3. Withdraw
    public String withdraw(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc == null) return "Account not found";

        if (acc.getBalance() < amount) {
            return "Insufficient Balance!";
        }

        acc.withdraw(amount);
        return "Success: Withdrawn $" + amount + ". New Balance: $" + acc.getBalance();
    }

    // 4. Transfer
    public String transfer(int fromAccNo, int toAccNo, double amount) {
        Account fromAcc = findAccount(fromAccNo);
        Account toAcc = findAccount(toAccNo);

        if (fromAcc == null || toAcc == null) return "One or both accounts not found";
        if (fromAcc.getBalance() < amount) return "Insufficient Balance for Transfer";

        fromAcc.withdraw(amount); // Deduct from sender
        toAcc.deposit(amount);    // Add to receiver

        return "Success: Transferred $" + amount + " to Account " + toAccNo;
    }

    // 5. Delete Account
    public String deleteAccount(int accNo) {
        Account acc = findAccount(accNo);
        if (acc == null) return "Account not found";

        accounts.remove(acc);
        return "Success: Account deleted successfully.";
    }
}