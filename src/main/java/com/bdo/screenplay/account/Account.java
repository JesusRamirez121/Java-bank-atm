package com.bdo.screenplay.account;

import com.bdo.screenplay.InsufficientFundsException;
import com.bdo.screenplay.iu.Authenticatable;
import com.bdo.screenplay.iu.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Authenticatable {
    private String accountNumber;
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notifica a los observadores con el saldo actualizado
    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(balance);
        }
    }

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

    public void deposit(double amount){
        balance += amount;
        notifyObservers();
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        balance -= amount;
        notifyObservers();
    }

    @Override
    public boolean authenticate(String pin) {
        return this.pin.equals(pin);
    }
}