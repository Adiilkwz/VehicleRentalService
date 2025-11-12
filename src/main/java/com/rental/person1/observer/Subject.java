package com.rental.person1.observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifySubscribers(String eventType, String message);
}