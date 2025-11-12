package com.rental.model;

public class RentalRequestDTO {
    private final String vehicleType;
    private final int durationMinutes;
    private final boolean addGps;
    private final boolean addInsurance;

    public RentalRequestDTO(String vehicleType, int durationMinutes, boolean addGps, boolean addInsurance) {
        this.vehicleType = vehicleType;
        this.durationMinutes = durationMinutes;
        this.addGps = addGps;
        this.addInsurance = addInsurance;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isAddGps() {
        return addGps;
    }

    public boolean isAddInsurance() {
        return addInsurance;
    }
}