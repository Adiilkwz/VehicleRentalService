package com.rental.model;

public class UserDTO {
    private final String userId;
    private final String email;
    private final String fullName;

    public UserDTO(String userId, String email, String fullName) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
}