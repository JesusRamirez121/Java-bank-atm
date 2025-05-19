package com.bdo.screenplay;

import java.util.ArrayList;
import java.util.List;

public class ATM {
    private List<Account> accounts;

    public ATM() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        Bank.incrementAccountCount();
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}