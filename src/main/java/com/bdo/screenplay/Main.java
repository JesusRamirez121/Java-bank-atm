package com.bdo.screenplay;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //variables basicas
        String accountNumber = "123456789";
        double balance = 1500.75;
        int pin = 1234;

        // Array de montos de transacciones
        int[] transactionAmounts = {200, -100, 50};

        //operaciones con variables
        balance += transactionAmounts[0]; // Deposito
        if (balance>0 && pin == 1234) {
            System.out.println("Transaction successful. New balance: " + balance);
        } else {
            System.out.println("Transaction failed. Insufficient funds or incorrect PIN.");
        }

        // uso de operadores
        balance++; // Incrementa el saldo
        String status = (balance < 0) ?"Deuda" : "Credito";
        System.out.println("Account status: " + status);
    }
}