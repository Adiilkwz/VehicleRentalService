package com.rental.person3;

import com.rental.person2.factory.Vehicle;
import com.rental.person2.strategy.PricingStrategy;
import com.rental.person3.decorator.BasicRental;
import com.rental.person3.decorator.GPSOption;
import com.rental.person3.decorator.InsuranceOption;
import com.rental.person3.decorator.RentalComponent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecoratorTest {

    private final double BASE_COST = 10.00;
    private final double GPS_FEE = 5.00;
    private final double INSURANCE_FEE = 8.50;
    private final int DURATION = 60;
    private final String VEHICLE_DESC = "Motorcycle";

    private Vehicle mockVehicle;
    private PricingStrategy mockStrategy;

    private void setupMocks() {
        mockVehicle = mock(Vehicle.class);
        mockStrategy = mock(PricingStrategy.class);

        when(mockVehicle.getDescription()).thenReturn(VEHICLE_DESC);
        when(mockStrategy.calculateCost(mockVehicle, DURATION)).thenReturn(BASE_COST);
    }

    @Test
    void testBasicRentalCostAndDescription() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);

        assertEquals(BASE_COST, basicRental.getCost(), 0.001);
        assertEquals("Basic rent: Motorcycle(60 name.)", basicRental.getDescription());
    }

    @Test
    void testNoOptionsCost() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);

        assertEquals(BASE_COST, basicRental.getCost(), 0.001);
    }


    @Test
    void testSingleDecoratorCost() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);
        RentalComponent gpsRental = new GPSOption(basicRental);

        double expectedCost = BASE_COST + GPS_FEE;

        assertEquals(expectedCost, gpsRental.getCost(), 0.001);
        assertEquals("Basic rent: Motorcycle(60 name.)+ GPS-navigator (+5.0$)", gpsRental.getDescription());
    }

    @Test
    void testStackedDecoratorsCostAndDescription() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);

        RentalComponent gpsRental = new GPSOption(basicRental);

        RentalComponent fullRental = new InsuranceOption(gpsRental);

        double expectedCost = BASE_COST + GPS_FEE + INSURANCE_FEE;

        assertEquals(expectedCost, fullRental.getCost(), 0.001);

        String expectedDescription = "Basic rent: Motorcycle(60 name.)+ GPS-navigator (+5.0$) + Full Insurance (+8.5$)";
        assertEquals(expectedDescription, fullRental.getDescription());
    }

    @Test
    void testReversedStackingOrder() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);

        RentalComponent insuranceRental = new InsuranceOption(basicRental);

        RentalComponent fullRentalReversed = new GPSOption(insuranceRental);

        double expectedCost = BASE_COST + GPS_FEE + INSURANCE_FEE;

        assertEquals(expectedCost, fullRentalReversed.getCost(), 0.001);

        String expectedDescriptionReversed = "Basic rent: Motorcycle(60 name.) + Full Insurance (+8.5$)+ GPS-navigator (+5.0$)";
        assertEquals(expectedDescriptionReversed, fullRentalReversed.getDescription());
    }

    @Test
    void testMultipleSameOption() {
        setupMocks();
        RentalComponent basicRental = new BasicRental(mockVehicle, DURATION, mockStrategy);

        RentalComponent insurance1 = new InsuranceOption(basicRental);
        RentalComponent insurance2 = new InsuranceOption(insurance1);

        double expectedCost = BASE_COST + INSURANCE_FEE + INSURANCE_FEE;

        assertEquals(expectedCost, insurance2.getCost(), 0.001);

        String expectedDescription = "Basic rent: Motorcycle(60 name.) + Full Insurance (+8.5$) + Full Insurance (+8.5$)";
        assertEquals(expectedDescription, insurance2.getDescription());
    }
}