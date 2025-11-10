package com.rental.person3.bridge.rental_bridge;

import com.rental.person3.bridge.payment_api.PaymentProcessor;

public abstract class OnlineRentalTransaction {
    protected PaymentProcessor paymentProcessor;

    public OnlineRentalTransaction(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    public abstract boolean executeTransaction(double amount);
}
