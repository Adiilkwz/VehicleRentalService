package com.rental.person2.factory;

public class Bike implements Vehicle{
    double bikeBasePriceHourly = 8.00;
    String bikeDescription = "Two-Wheeled Commuter Bike";
    int minRentalMinutes = 30;
    int maxRentalMinutes = 1440;

    @Override
    public double getBasePrice(){
        return bikeBasePriceHourly;
    }

    @Override
    public String getDescription(){
        return bikeDescription;
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
