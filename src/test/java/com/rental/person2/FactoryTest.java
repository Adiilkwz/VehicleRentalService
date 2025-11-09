package com.rental.person2;

import com.rental.person2.factory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    @Test
    void testCreateCarSuccess() {
        Vehicle car = VehicleFactory.createVehicle("CAR");
        assertTrue(car instanceof Car, "Factory should return a Car instance.");
        assertEquals("Standard Sedan", car.getDescription(), "Car description is incorrect.");
        assertEquals(45.00, car.getBasePrice(), 0.001, "Car base price is incorrect.");
    }

    @Test
    void testCreateBikeSuccess() {
        Vehicle bike = VehicleFactory.createVehicle("BIKE");
        assertTrue(bike instanceof Bike, "Factory should return a Bike instance.");
    }

    @Test
    void testCreateElectricScooterSuccess() {
        Vehicle scooter = VehicleFactory.createVehicle("SCOOTER");
        assertTrue(scooter instanceof ElectricScooter, "Factory should return an ElectricScooter instance.");
    }

    @Test
    void testCreateVehicleInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VehicleFactory.createVehicle("TRUCK");
        });
        String expectedMessage = "Invalid vehicle type: TRUCK";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCarConstraints() {
        Vehicle car = VehicleFactory.createVehicle("CAR");
        assertEquals(240, car.getMinMinutes(), "Car minimum rental minutes should be 240.");
        assertEquals(4320, car.getMaxMinutes(), "Car maximum rental minutes should be 4320.");
    }

    @Test
    void testScooterConstraints() {
        Vehicle scooter = VehicleFactory.createVehicle("SCOOTER");
        assertEquals(15, scooter.getMinMinutes(), "Scooter minimum rental minutes should be 15.");
        assertEquals(180, scooter.getMaxMinutes(), "Scooter maximum rental minutes should be 180.");
    }
}