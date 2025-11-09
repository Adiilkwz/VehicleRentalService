package com.rental.person2.strategy;

import com.rental.person2.factory.Vehicle;

public class MinuteStrategy implements PricingStrategy{
    private static final int min_per_hour = 60;

    @Override
    public double calculateCost(Vehicle vehicle, int duration_minutes){
        if (duration_minutes <= 0){
            return 0.00;
        }
        double hourly_rate = vehicle.getBasePrice();

        double rate_per_minute = hourly_rate / min_per_hour;

        double total_cost = rate_per_minute * duration_minutes;

        return Math.round(total_cost * 100.0) / 100.0;
    }
}
