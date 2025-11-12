package com.rental.person1.observer;

public class UserNotificationListener implements Observer {

    private int notificationCount = 0;

    @Override
    public void update(String eventType, String message) {
        if ("BOOKING_SUCCESS".equals(eventType)) {
            notificationCount++;
            System.out.println("User Notification: Booking successful! UI update triggered. New count: " + notificationCount);
        } else if ("PAYMENT_FAILED".equals(eventType)) {
            notificationCount++;
            System.out.println("User Notification: Payment error occurred. New count: " + notificationCount);
        }
    }

    public int getNotificationCount() {
        return notificationCount;
    }
}