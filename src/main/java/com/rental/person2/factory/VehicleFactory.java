package com.rental.person2.factory;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType){
        return switch (vehicleType.toUpperCase()) {
            case "CAR" -> new Car();
            case "BIKE" -> new Bike();
            case "ELECTRIC SCOOTER", "SCOOTER" -> new ElectricScooter();
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        };
    }
}
