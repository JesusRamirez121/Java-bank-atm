package com.bdo.screenplay;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double deposit(double amount) {
        balance += amount;
        transactionHistory.add("Depósito: $" + amount);
        return balance;
    }

    public double withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Retiro: $" + amount);
            return balance;
        } else {
            System.out.println("Fondos insuficientes");
            return balance;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}