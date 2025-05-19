package com.bdo.screenplay;

import com.bdo.screenplay.iu.PaymentGateway;

public class PayPalAdapter implements PaymentGateway {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    @Override
    public void pay(double amount) {
        payPal.sendPayment(amount);
    }

}
