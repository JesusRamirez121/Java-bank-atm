package com.bdo.screenplay;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private int pin = 1234;

    public static void main(String[] args) {
        Main mainApp = new Main();
        Scanner scanner = new Scanner(System.in);

        // Autenticación antes del menú
        System.out.print("Ingrese su PIN: ");
        String inputPin = scanner.nextLine();
        if (!mainApp.authenticateUser(inputPin)) {
            System.out.println("Acceso denegado.");
            scanner.close();
            return;
        }

        double balance = 1500.75;
        boolean running = true;
        ArrayList<String> transactionHistory = new ArrayList<>();

        //balance = deposit(500, balance, transactionHistory);
        //balance = withdraw(100, balance, transactionHistory);

        while (running) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Transacciones internacionales");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: " + balance);
                    break;
                case 2:
                    System.out.print("Ingrese monto a depositar: ");
                    double deposito = scanner.nextDouble();
                    balance = deposit(deposito, balance, transactionHistory);
                    System.out.println("Depósito exitoso. Nuevo saldo: " + balance);
                    break;
                case 3:
                    System.out.print("Ingrese monto a retirar: ");
                    double retiro = scanner.nextDouble();
                    double nuevoBalance = withdraw(retiro, balance, transactionHistory);
                    if (nuevoBalance != balance) {
                        balance = nuevoBalance;
                        System.out.println("Retiro exitoso. Nuevo saldo: " + balance);
                    } else {
                        System.out.println("Fondos insuficientes.");
                    }
                    break;
                case 4:
                    System.out.println("Transacciones internacionales no disponibles en esta versión.");
                    break;
                case 5:
                    running = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    public boolean authenticateUser(String inputPin) {
        int attempts = 0;
        while (attempts < 3) {
            if (String.valueOf(this.pin).equals(inputPin)) {
                System.out.println("Usuario autenticado correctamente.");
                return true;
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("PIN incorrecto. Intentos restantes: " + (3 - attempts));
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Ingrese su PIN: ");
                    inputPin = scanner.nextLine();
                }
            }
        }
        return false;
    }

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