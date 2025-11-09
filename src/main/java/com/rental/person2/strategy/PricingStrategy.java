package com.rental.person2.strategy;

import com.rental.person2.factory.Vehicle;

public interface PricingStrategy {
    double calculateCost(Vehicle vehicle, int duration_minutes);
}
