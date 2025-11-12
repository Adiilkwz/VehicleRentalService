package com.rental.model;

public class BookingReceipt {
    private final boolean success;
    private final double finalCost;
    private final String message;

    public BookingReceipt(boolean success, double finalCost, String message) {
        this.success = success;
        this.finalCost = finalCost;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public String getMessage() {
        return message;
    }
}