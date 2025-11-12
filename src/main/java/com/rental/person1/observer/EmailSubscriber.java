package com.rental.person1.observer;

public class EmailSubscriber implements Observer {

    private final String emailAddress;

    public EmailSubscriber(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void update(String eventType, String message) {
        if ("BOOKING_SUCCESS".equals(eventType)) {
            System.out.println("EMAIL SENT to " + emailAddress + ": Successfully completed booking. Details: " + message);
        }
    }
}