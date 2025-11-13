package com.rental.person1.observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebNotificationService implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer attached: " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void detach(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
            System.out.println("Observer detached: " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void notifySubscribers(String eventType, String message) {
        System.out.println("--- Notifying subscribers of event: " + eventType + " ---");
        for (Observer observer : new ArrayList<>(observers)) {
            observer.update(eventType, message);
        }
    }
    public void notifyObservers(String bookingSuccess, String notificationMessage) {
    }
}