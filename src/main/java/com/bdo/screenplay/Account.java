package com.bdo.screenplay;

import java.util.ArrayList;

public abstract class Account implements Authenticatable{
    private String accountNumber;
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;


    public Account(String accountNumber, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    @Override
    public boolean authenticate(String pin) {
        return this.pin.equals(pin);
    }
}