package com.bdo.screenplay.account;

import com.bdo.screenplay.InsufficientFundsException;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double initialBalance, String pin, double overdraftLimit) {
        super(accountNumber, initialBalance, pin);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
        try {
            java.lang.reflect.Field balanceField = Account.class.getDeclaredField("balance");
            balanceField.setAccessible(true);
            double currentBalance = getBalance();
            balanceField.set(this, currentBalance + amount);
            getTransactionHistory().add("Depósito: $" + amount);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el saldo.", e);
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() + overdraftLimit >= amount) {
            deposit(-amount);
            getTransactionHistory().add("Retiro (cuenta corriente): $" + amount);
        } else {
            throw new InsufficientFundsException("Fondos insuficientes (incluyendo sobregiro)");
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}