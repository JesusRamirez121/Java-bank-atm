package com.bdo.screenplay;

import java.util.ArrayList;
import java.util.Scanner;

import static com.bdo.screenplay.Transations.deposit;
import static com.bdo.screenplay.Transations.withdraw;

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

        while (running) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Transacciones internacionales");
            System.out.println("5. Calculadora");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: " + balance);
                    break;
                case 2:
                    Transations transation = new Transations();
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
                    Calculator calc = new Calculator();
                    System.out.println("--- Calculadora ---");
                    System.out.print("Ingrese el primer número: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    double num2 = scanner.nextDouble();
                    System.out.println("Seleccione operación: 1) Sumar 2) Restar 3) Multiplicar 4) Dividir");
                    int op = scanner.nextInt();
                    try {
                        double resultado = switch (op) {
                            case 1 -> calc.add(num1, num2);
                            case 2 -> calc.subtract(num1, num2);
                            case 3 -> calc.multiply(num1, num2);
                            case 4 -> calc.divide(num1, num2);
                            default -> {
                                System.out.println("Operación no válida.");
                                yield Double.NaN;
                            }
                        };
                        if (!Double.isNaN(resultado)) {
                            System.out.println("Resultado: " + resultado);
                        }
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
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


}