package com.rental.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingReceipt {

    private final String bookingId;
    private final boolean success;
    private final double finalCost;
    private final String message;
    private final LocalDateTime issueTime;

    public BookingReceipt(boolean success, double finalCost, String message) {
        this.bookingId = success ? UUID.randomUUID().toString() : "N/A";
        this.success = success;
        this.finalCost = finalCost;
        this.message = message;
        this.issueTime = LocalDateTime.now();
    }

    public String getBookingId() {
        return bookingId;
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

    public LocalDateTime getIssueTime() {
        return issueTime;
    }
}