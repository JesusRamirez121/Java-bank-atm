package com.bdo.screenplay.notifier;

import com.bdo.screenplay.iu.Observer;

public class EmailNotifier implements Observer {
    @Override
    public void update(double balance) {
        System.out.println("Email Notification: Your new balance is " + balance);
    }
}
