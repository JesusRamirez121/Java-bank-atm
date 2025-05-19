package com.bdo.screenplay;

import com.bdo.screenplay.account.Account;
import com.bdo.screenplay.iu.AuthStrategy;
import com.bdo.screenplay.iu.Authenticatable;

import java.util.ArrayList;
import java.util.List;

public class ATM implements Authenticatable {
    private List<Account> accounts;
    private AuthStrategy authStrategy;

    public void setAuthStrategy(AuthStrategy authStrategy){
        this.authStrategy = authStrategy;
    }

    public boolean authenticateUser(String data) {
        return authStrategy.authenticate(data);
    }

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

    @Override
    public boolean authenticate(String pin) {
        // Lógica simple de autenticación, puedes personalizarla según tu necesidad
        return "1234".equals(pin);
    }
}