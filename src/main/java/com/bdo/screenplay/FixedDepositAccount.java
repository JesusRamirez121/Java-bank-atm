package com.bdo.screenplay;

import java.time.LocalDate;

public class FixedDepositAccount extends Account {
    private double interesFixedDeposit;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private double penaltyRate;

    public FixedDepositAccount(String accountNumber, double initialBalance, String pin, double interesFixedDeposit,
                               LocalDate startDate, LocalDate maturityDate, double penaltyRate) {
        super(accountNumber, initialBalance, pin);
        this.interesFixedDeposit = interesFixedDeposit;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.penaltyRate = penaltyRate;
    }

    @Override
    public void deposit(double amount) {
        // Solo permite depósitos positivos
        if (amount > 0) {
            // Actualiza el saldo usando el método deposit de la superclase
            // Como balance es privado, puedes usar reflection o modificar Account para permitir modificar el saldo.
            // Aquí se asume que puedes modificar el saldo directamente o mediante un método protegido.
            // Si no puedes, deberías agregar un método protegido en Account para modificar el saldo.
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
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (LocalDate.now().isBefore(maturityDate)) {
            double penalty = amount * penaltyRate / 100;
            double totalDeduct = amount + penalty;
            if (getBalance() >= totalDeduct) {
                deposit(-totalDeduct);
                getTransactionHistory().add("Retiro anticipado: $" + amount + ", Penalización: $" + penalty);
            } else {
                throw new InsufficientFundsException("Fondos insuficientes para retiro anticipado.");
            }
        } else {
            if (getBalance() >= amount) {
                deposit(-amount);
                getTransactionHistory().add("Retiro tras vencimiento: $" + amount);
            } else {
                throw new InsufficientFundsException("Fondos insuficientes para retiro tras vencimiento.");
            }
        }
    }

    public void applyInterest() {
        if (LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate)) {
            double interest = getBalance() * interesFixedDeposit / 100;
            deposit(interest);
            getTransactionHistory().add("Interés aplicado: $" + interest);
        } else {
            System.out.println("El plazo no ha vencido. No se puede aplicar interés.");
        }
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }
}