package com.rental.person3.bridge.payment_api;

public class StripeProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("[String API]: Payment processing $%.2f. successfully.%n", amount);
        return true;
    }
    @Override
    public String getName() {
        return "Stripe";
    }
}

