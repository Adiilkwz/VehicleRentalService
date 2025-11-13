package com.rental.model;

public class RentalRequestDTO {
    private String vehicleType;
    private int durationMinutes;
    private boolean addGps;
    private boolean addInsurance;
    private String paymentToken;
    private UserDTO user;

    public RentalRequestDTO() {
        this.user = new UserDTO();
    }

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

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}