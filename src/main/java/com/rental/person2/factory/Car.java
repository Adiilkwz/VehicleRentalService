package com.rental.person2.factory;

public class Car implements Vehicle{
    double carBasePriceHourly = 45.00;
    String carDescription = "Standard Sedan";
    int minRentalMinutes = 240;
    int maxRentalMinutes = 4320;

    @Override
    public double getBasePrice(){
        return carBasePriceHourly;
    }

    @Override
    public String getDescription(){
        return carDescription;
    }

    @Override
    public int getMinMinutes(){
        return minRentalMinutes;
    }

    @Override
    public int getMaxMinutes(){
        return maxRentalMinutes;
    }
}
