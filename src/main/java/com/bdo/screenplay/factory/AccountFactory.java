package com.bdo.screenplay.factory;

import com.bdo.screenplay.account.Account;

public abstract class AccountFactory {
    public abstract Account createAccount(String accountType, String accountNumber, double initialBalance, String pin, double interestRate);
    public abstract Account createAccount(String accountType, String accountNumber, double initialBalance, String pin);
}