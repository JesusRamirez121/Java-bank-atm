package com.bdo.screenplay;

import java.time.LocalDate;

public class FixedDepositAccount extends Account {
    private double interesFixedDeposit;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private double penaltyRate;

    public FixedDepositAccount(String accountNumber, double initialBalance, double interesFixedDeposit,
                               LocalDate startDate, LocalDate maturityDate, double penaltyRate) {
        super(accountNumber, initialBalance);
        this.interesFixedDeposit = interesFixedDeposit;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.penaltyRate = penaltyRate;
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

    @Override
    public double withdraw(double amount) {
        if (LocalDate.now().isBefore(maturityDate)) {
            double penalty = amount * penaltyRate / 100;
            double totalDeduct = amount + penalty;
            if (getBalance() >= totalDeduct) {
                deposit(-totalDeduct);
                getTransactionHistory().add("Retiro anticipado: $" + amount + ", Penalización: $" + penalty);
                return getBalance();
            } else {
                System.out.println("Fondos insuficientes para retiro anticipado.");
                return getBalance();
            }
        } else {
            return super.withdraw(amount);
        }
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }
}