package com.bdo.screenplay.factory;

import com.bdo.screenplay.account.Account;
import com.bdo.screenplay.account.CheckingAccount;
import com.bdo.screenplay.account.SavingsAccount;

public class ConcreteAccountFactory extends AccountFactory {
    @Override
    public Account createAccount(String accountType, String accountNumber, double initialBalance, String pin, double interestRate) {
        if (accountType.equalsIgnoreCase("savings")) {
            return new SavingsAccount(accountNumber, initialBalance, pin, interestRate);
        }
        throw new IllegalArgumentException("Tipo de cuenta no válido para este constructor.");
    }

    @Override
    public Account createAccount(String accountType, String accountNumber, double initialBalance, String pin) {
        if (accountType.equalsIgnoreCase("checking")) {
            return new CheckingAccount(accountNumber, initialBalance, pin, 0);
        }
        throw new IllegalArgumentException("Tipo de cuenta no válido para este constructor.");
    }
}