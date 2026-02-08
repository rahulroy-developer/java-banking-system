package com.example.banksystem.controller;

import com.example.banksystem.service.BankService;
import com.example.banksystem.model.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows the frontend to talk to this backend
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // Create Account Endpoint
    @PostMapping("/create")
    public String createAccount(@RequestParam String name, @RequestParam int accNo,
                                @RequestParam double balance, @RequestParam String type) {
        return bankService.createAccount(name, accNo, balance, type);
    }

    // Get Balance Endpoint
    @GetMapping("/balance/{accNo}")
    public Account getBalance(@PathVariable int accNo) {
        return bankService.findAccount(accNo);
    }

    // Deposit Endpoint
    @PostMapping("/deposit")
    public String deposit(@RequestParam int accNo, @RequestParam double amount) {
        return bankService.deposit(accNo, amount);
    }

    // Withdraw Endpoint
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam int accNo, @RequestParam double amount) {
        return bankService.withdraw(accNo, amount);
    }

    // Transfer Endpoint
    @PostMapping("/transfer")
    public String transfer(@RequestParam int fromAcc, @RequestParam int toAcc, @RequestParam double amount) {
        return bankService.transfer(fromAcc, toAcc, amount);
    }

    // Delete Account Endpoint
    @DeleteMapping("/delete/{accNo}")
    public String deleteAccount(@PathVariable int accNo) {
        return bankService.deleteAccount(accNo);
    }
}