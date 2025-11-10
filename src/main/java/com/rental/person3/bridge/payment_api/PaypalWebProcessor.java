package com.rental.person3.bridge.payment_api;

public class PaypalWebProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("[Paypal Web]: Redirection and successful payment $%.2f.%n", amount);
        return true;
    }
    @Override
    public String getName() {
        return "PayPal";
    }
}
