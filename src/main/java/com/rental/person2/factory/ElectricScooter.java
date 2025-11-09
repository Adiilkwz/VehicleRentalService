package com.rental.person2.factory;

public class ElectricScooter implements Vehicle{
    double scooterBasePriceHourly= 5.00;
    String scooterDescription = "High-Speed Electric Scooter";
    int minRentalMinutes = 15;
    int maxRentalMinutes = 180;

    @Override
    public double getBasePrice(){
        return scooterBasePriceHourly;
    }

    @Override
    public String getDescription(){
        return scooterDescription;
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
