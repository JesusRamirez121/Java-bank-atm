package com.bdo.screenplay.transactions;

import com.bdo.screenplay.iu.Transaction;

public class SimpleTransaction implements Transaction {
    private String description;

    public SimpleTransaction(String description) {
        this.description = description;
    }

    @Override
    public void execute() {
        System.out.println("Executing transaction: " + description);
    }
}
