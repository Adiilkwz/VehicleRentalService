package com.rental.person2.strategy;

import com.rental.person2.factory.Vehicle;

public class HourlyStrategy implements PricingStrategy{
    @Override
    public double calculateCost(Vehicle vehicle, int duration_minutes){
        double hourly_rate = vehicle.getBasePrice();

        if (duration_minutes <= 0) {
            return 0.00;
        }

        double duration_hours = (double) duration_minutes / 60.0;

        long billed_hours = (long) Math.ceil(duration_hours);

        double cost = billed_hours * hourly_rate;

        return Math.round(cost * 100.0) / 100.0;
    }
}
