package com.rental.person1.facade;

import com.rental.model.BookingReceipt;
import com.rental.model.RentalRequestDTO;
import com.rental.person1.observer.WebNotificationService;
import com.rental.person2.factory.Vehicle;
import com.rental.person2.factory.VehicleFactory;
import com.rental.person2.strategy.PricingStrategy;
import com.rental.person2.strategy.HourlyStrategy;
import com.rental.person3.bridge.payment_api.PaymentProcessor;
import com.rental.person3.bridge.payment_api.StripeProcessor;
import com.rental.person3.bridge.rental_bridge.SecureWebTransaction;
import com.rental.person3.decorator.BasicRental;
import com.rental.person3.decorator.GPSOption;
import com.rental.person3.decorator.InsuranceOption;
import com.rental.person3.decorator.RentalComponent;
import org.springframework.stereotype.Service;

@Service
public class WebRentalFacade {

    private final WebNotificationService notificationService;

    public WebRentalFacade(WebNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public BookingReceipt completeBooking(RentalRequestDTO request) {
        try {
            Vehicle vehicle = VehicleFactory.createVehicle(request.getVehicleType());

            int durationMinutes = request.getDurationMinutes();

            if (durationMinutes < vehicle.getMinMinutes() || durationMinutes > vehicle.getMaxMinutes()) {
                return new BookingReceipt(false, 0.0,
                        "Error: Rental duration must be between " + vehicle.getMinMinutes() + " and " + vehicle.getMaxMinutes() + " minutes.");
            }

            PricingStrategy pricingStrategy = new HourlyStrategy();

            RentalComponent rental = new BasicRental(vehicle, durationMinutes, pricingStrategy);

            if (request.isAddGps()) {
                rental = new GPSOption(rental);
            }
            if (request.isAddInsurance()) {
                rental = new InsuranceOption(rental);
            }

            double finalCost = rental.getCost();

            PaymentProcessor processor = new StripeProcessor();

            SecureWebTransaction transaction = new SecureWebTransaction(processor);

            boolean success = transaction.executeTransaction(finalCost);

            if (!success) {
                return new BookingReceipt(false, finalCost, "Payment Error: Transaction failed.");
            }

            String notificationMessage = String.format(
                    "Booking for %s for %d minutes (Amount: %.2f$) successfully completed.",
                    vehicle.getDescription(), durationMinutes, finalCost);

            notificationService.notifySubscribers("BOOKING_SUCCESS", notificationMessage);

            return new BookingReceipt(true, finalCost, "Successfully booked: " + rental.getDescription());

        } catch (Exception e) {
            return new BookingReceipt(false, 0.0, "Internal System Error: " + e.getMessage());
        }
    }
}