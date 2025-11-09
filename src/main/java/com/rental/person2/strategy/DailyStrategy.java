package com.rental.person2.strategy;

import com.rental.person2.factory.Vehicle;

public class DailyStrategy implements PricingStrategy{
    private static final double daily_rate_factor = 0.8;
    private static final int minutes_in_day = 1440;

    @Override
    public double calculateCost(Vehicle vehicle, int duration_minutes){
        if (duration_minutes <= 0){
            return 0.00;
        }
        double hourly_rate = vehicle.getBasePrice();

        double daily_rate = hourly_rate * 24 * daily_rate_factor;

        double duration_days = (double) duration_minutes / minutes_in_day;

        long billed_days = (long) Math.ceil(duration_days);

        double cost = billed_days * daily_rate;

        return Math.round(cost * 100.0) / 100.0;
    }
}
