package com.rental.person3.bridge.payment_api;

public interface PaymentProcessor {
    boolean processPayment(double amount);

    String getName();
}
