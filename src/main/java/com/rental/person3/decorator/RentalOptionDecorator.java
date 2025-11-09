package com.rental.person3.decorator;

public abstract class RentalOptionDecorator implements RentalComponent {
    protected RentalComponent wrappedComponent;

    public RentalOptionDecorator(RentalComponent wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
    }
}
