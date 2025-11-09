package com.rental.person3.decorator;

public class InsuranceOption extends RentalOptionDecorator {
    private static final double INSURANCE_FEE = 8.50;

    public InsuranceOption(RentalComponent wrappedComponent) {
        super(wrappedComponent);
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost() + INSURANCE_FEE;
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + " + Full Insurance (+" + INSURANCE_FEE + "$)";
    }
}
