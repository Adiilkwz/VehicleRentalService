package com.rental.person3.decorator;

public class GPSOption extends RentalOptionDecorator {
    private static final double GPS_FEE = 5.00;

    public GPSOption(RentalComponent wrappedComponent){
        super(wrappedComponent);
    }

    @Override
    public double getCost() {
        return wrappedComponent.getCost() + GPS_FEE;
    }

    @Override
    public String getDescription() {
        return wrappedComponent.getDescription() + "+ GPS-navigator (+" + GPS_FEE + "$)";
    }
}
