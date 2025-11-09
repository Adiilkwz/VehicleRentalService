package com.rental.person2;

import com.rental.person2.factory.Vehicle;
import com.rental.person2.factory.Car;
import com.rental.person2.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    private Vehicle car;

    @BeforeEach
    void setup() {
        this.car = new Car();
    }

    @Test
    void testMinuteStrategyShortRental() {
        PricingStrategy strategy = new MinuteStrategy();
        double cost = strategy.calculateCost(car, 15);
        assertEquals(11.25, cost, 0.01, "Minute Strategy cost for 15 min is incorrect.");
    }

    @Test
    void testMinuteStrategyFractionalCost() {
        PricingStrategy strategy = new MinuteStrategy();
        double cost = strategy.calculateCost(car, 36);
        assertEquals(27.00, cost, 0.01, "Minute Strategy cost for 36 min is incorrect.");
    }

    @Test
    void testMinuteStrategyZeroDuration() {
        PricingStrategy strategy = new MinuteStrategy();
        double cost = strategy.calculateCost(car, 0);
        assertEquals(0.00, cost, "Zero duration should cost $0.00.");
    }

    @Test
    void testHourlyStrategyExactHours() {
        PricingStrategy strategy = new HourlyStrategy();
        double cost = strategy.calculateCost(car, 120);
        assertEquals(90.00, cost, 0.01, "Hourly Strategy cost for 120 min is incorrect.");
    }

    @Test
    void testHourlyStrategyRoundsUp() {
        PricingStrategy strategy = new HourlyStrategy();
        double cost = strategy.calculateCost(car, 121);
        assertEquals(135.00, cost, 0.01, "Hourly Strategy should round 121 min up to 3 hours.");
    }

    @Test
    void testHourlyStrategyShortDuration() {
        PricingStrategy strategy = new HourlyStrategy();
        double cost = strategy.calculateCost(car, 1);
        assertEquals(45.00, cost, 0.01, "Hourly Strategy should round 1 min up to 1 hour.");
    }

    private static final double EXPECTED_DAILY_RATE = 864.00;

    @Test
    void testDailyStrategyExactDay() {
        PricingStrategy strategy = new DailyStrategy();
        double cost = strategy.calculateCost(car, 1440);
        assertEquals(EXPECTED_DAILY_RATE, cost, 0.01, "Daily Strategy cost for 1440 min is incorrect.");
    }

    @Test
    void testDailyStrategyRoundsUp() {
        PricingStrategy strategy = new DailyStrategy();
        double cost = strategy.calculateCost(car, 1441);
        assertEquals(1728.00, cost, 0.01, "Daily Strategy should round 1441 min up to 2 days.");
    }

    @Test
    void testDailyStrategyLessThanDay() {
        PricingStrategy strategy = new DailyStrategy();
        double cost = strategy.calculateCost(car, 1200);
        assertEquals(EXPECTED_DAILY_RATE, cost, 0.01, "Daily Strategy for 20 hours should round up to 1 day.");
    }
}