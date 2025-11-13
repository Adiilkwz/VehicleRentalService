package com.rental.person3;

import com.rental.person3.bridge.payment_api.PaymentProcessor;
import com.rental.person3.bridge.payment_api.PaypalWebProcessor;
import com.rental.person3.bridge.payment_api.StripeProcessor;
import com.rental.person3.bridge.rental_bridge.OnlineRentalTransaction;
import com.rental.person3.bridge.rental_bridge.SecureWebTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BridgeTest {

    private final double TEST_AMOUNT = 150.75;

    @Test
    void testStripeProcessorIntegration() {
        PaymentProcessor stripeProcessor = new StripeProcessor();
        OnlineRentalTransaction transaction = new SecureWebTransaction(stripeProcessor);
        assertTrue(transaction.executeTransaction(TEST_AMOUNT));
    }

    @Test
    void testPaypalProcessorIntegration() {
        PaymentProcessor paypalProcessor = new PaypalWebProcessor();
        OnlineRentalTransaction transaction = new SecureWebTransaction(paypalProcessor);
        assertTrue(transaction.executeTransaction(TEST_AMOUNT));
    }

    @Test
    void testFailureHandling() {
        PaymentProcessor mockFailureProcessor = mock(PaymentProcessor.class);
        when(mockFailureProcessor.processPayment(TEST_AMOUNT)).thenReturn(false);

        OnlineRentalTransaction transaction = new SecureWebTransaction(mockFailureProcessor);
        assertFalse(transaction.executeTransaction(TEST_AMOUNT));
    }

    @Test
    void testProcessorNameDelegation() {
        PaymentProcessor mockProcessor = mock(PaymentProcessor.class);
        when(mockProcessor.getName()).thenReturn("MockProcessor");

        assertEquals("MockProcessor", mockProcessor.getName());
    }

    @Test
    void testStripeProcessorName() {
        PaymentProcessor stripeProcessor = new StripeProcessor();
        assertEquals("Stripe", stripeProcessor.getName());
    }

    @Test
    void testTransactionWithDifferentAmount() {
        PaymentProcessor stripeProcessor = new StripeProcessor();
        OnlineRentalTransaction transaction = new SecureWebTransaction(stripeProcessor);
        double largeAmount = 9999.99;
        assertTrue(transaction.executeTransaction(largeAmount));
    }

    @Test
    void testTransactionWithZeroAmount() {
        PaymentProcessor paypalProcessor = new PaypalWebProcessor();
        OnlineRentalTransaction transaction = new SecureWebTransaction(paypalProcessor);
        double zeroAmount = 0.00;
        assertTrue(transaction.executeTransaction(zeroAmount));
    }
}