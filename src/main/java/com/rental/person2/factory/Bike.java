package com.rental.person2.factory;

public class Bike implements Vehicle{
    double bike_base_price = 15.00;
    String bike_description = "Two-Wheeled Commuter Bike";

    @Override
    public double GetBasePrice(){
        return bike_base_price;
    }

    @Override
    public String GetDescription(){
        return bike_description;
    }

    @Override
    public String GetTypeIdentifier(){
        return "Bike";
    }
}
