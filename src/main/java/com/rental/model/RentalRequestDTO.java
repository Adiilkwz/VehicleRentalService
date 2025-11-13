package com.rental.model;

import java.util.List;

public class RentalRequestDTO {

    public RentalRequestDTO() {
    }

    private String vehicleType;
    private int durationMinutes;
    private boolean addGps;
    private boolean addInsurance;
    private UserDTO user;
    private String paymentToken;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isAddGps() {
        return addGps;
    }

    public void setAddGps(boolean addGps) {
        this.addGps = addGps;
    }

    public boolean isAddInsurance() {
        return addInsurance;
    }

    public void setAddInsurance(boolean addInsurance) {
        this.addInsurance = addInsurance;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }
}