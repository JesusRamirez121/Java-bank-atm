package com.bdo.screenplay;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) {
            double newBalance = getBalance() - amount;
            // Si el saldo es negativo, se está usando el sobregiro
            // Actualiza el saldo y registra la transacción
            // Usamos el método deposit(-amount) para restar
            deposit(-amount);
            getTransactionHistory().add("Retiro (cuenta corriente): $" + amount);
            return getBalance();
        } else {
            System.out.println("Fondos insuficientes (incluyendo sobregiro)");
            return getBalance();
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}