package com.bdo.screenplay.account;

import com.bdo.screenplay.InsufficientFundsException;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, String pin, double interestRate) {
        super(accountNumber, initialBalance, pin);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        setBalance(getBalance() + amount);
        getTransactionHistory().add("Depósito: $" + amount);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            getTransactionHistory().add("Retiro: $" + amount);
        } else {
            throw new InsufficientFundsException("Fondos insuficientes para retiro.");
        }
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        getTransactionHistory().add("Interés aplicado: $" + interest);
    }
}