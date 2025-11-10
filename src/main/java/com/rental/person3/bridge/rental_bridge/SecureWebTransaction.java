package com.rental.person3.bridge.rental_bridge;

import com.rental.person3.bridge.payment_api.PaymentProcessor;

public class SecureWebTransaction extends OnlineRentalTransaction {
    public SecureWebTransaction(PaymentProcessor paymentProcessor) {
        super(paymentProcessor);
    }

    @Override
    public boolean executeTransaction(double amount) {
        System.out.println("---Secure Web Transaction Begins---");
        System.out.println("The processor is being used: " + paymentProcessor.getName());

        return paymentProcessor.processPayment(amount);
    }
}
