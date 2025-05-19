package com.bdo.screenplay.transactions;

import com.bdo.screenplay.iu.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CompositeTransaction implements Transaction {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void execute() {
        for (Transaction t : transactions) {
            t.execute();
        }
    }
}
