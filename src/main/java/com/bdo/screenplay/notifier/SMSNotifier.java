package com.bdo.screenplay.notifier;

import com.bdo.screenplay.iu.Observer;

public class SMSNotifier implements Observer {
    @Override
    public void update(double balance) {
        System.out.println("SMS Notification: Your new balance is " + balance);
    }
}
