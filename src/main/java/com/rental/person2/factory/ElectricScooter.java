package com.rental.person2.factory;

public class ElectricScooter implements Vehicle{
    double scooter_base_price = 8.00;
    String scooter_description = "High-Speed Electric Scooter";

    @Override
    public double GetBasePrice(){
        return scooter_base_price;
    }

    @Override
    public String GetDescription(){
        return scooter_description;
    }

    @Override
    public String GetTypeIdentifier(){
        return "Electric Scooter";
    }
}
