package com.rental.person3.decorator;

import com.rental.person2.factory.Vehicle;
import com.rental.person2.strategy.PricingStrategy;

public class BasicRental implements RentalComponent {

    private final Vehicle vehicle;
    private final int durationInMinutes;
    private final PricingStrategy strategy;

    public BasicRental(Vehicle vehicle, int durationInMinutes, PricingStrategy strategy) {
        this.vehicle = vehicle;
        this.durationInMinutes = durationInMinutes;
        this.strategy = strategy;
    }

    @Override
    public double getCost() {
        return strategy.calculateCost(this.vehicle, this.durationInMinutes);
    }

    @Override
    public String getDescription() {
        return "Basic rent: " + vehicle.getDescription() + "(" + this.durationInMinutes + " name.)";
    }
}
