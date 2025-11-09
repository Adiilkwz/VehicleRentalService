package com.rental.person2.factory;

public class Car implements Vehicle{
    double car_base_price = 45.00;
    String car_description = "Standard Sedan";

    @Override
    public double GetBasePrice(){
        return car_base_price;
    }

    @Override
    public String GetDescription(){
        return car_description;
    }

    @Override
    public String GetTypeIdentifier(){
        return "Car";
    }
}
