package com.bdo.screenplay;

import java.util.ArrayList;

public class Transations {
    public static double deposit(double amount, double balance, ArrayList<String> transactionHistory) {
        balance += amount;
        transactionHistory.add("Depósito: $" + amount);
        return balance;
    }

    public static double withdraw(double amount, double balance, ArrayList<String> transactionHistory) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Retiro: $" + amount);
            return balance;
        } else {
            System.out.println("Fondos insuficientes");
            return balance;
        }
    }
}
