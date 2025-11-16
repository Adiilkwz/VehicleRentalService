package com.rental.person1;

import com.rental.model.BookingReceipt;
import com.rental.model.RentalRequestDTO;
import com.rental.model.UserDTO;
import com.rental.person1.facade.WebRentalFacade;
import com.rental.person1.observer.WebNotificationService;
import com.rental.person3.bridge.rental_bridge.SecureWebTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacadeTest {

    @Mock
    private WebNotificationService notificationService;

    private WebRentalFacade facade;
    private RentalRequestDTO request;

    @BeforeEach
    void setUp() {
        facade = new WebRentalFacade(notificationService);
        request = new RentalRequestDTO();
        request.setVehicleType("Car");
        request.setDurationMinutes(300); // 5 часов
        request.setAddGps(false);
        request.setAddInsurance(false);
        request.setPaymentToken("Stripe");

        UserDTO user = new UserDTO();
        user.setName("Test User");
        request.setUser(user);
    }

    @Test
    void testCompleteBookingSuccess() {
        BookingReceipt receipt = facade.completeBooking(request);

        assertTrue(receipt.isSuccess());
        assertEquals(225.00, receipt.getFinalCost());
        assertEquals("Successfully booked: Basic rent: Standard Sedan(300 name.)", receipt.getMessage());

        verify(notificationService, times(1)).notifySubscribers(eq("BOOKING_SUCCESS"), anyString());
    }

    @Test
    void testCompleteBookingWithDecorators() {
        request.setAddGps(true);
        request.setAddInsurance(true);

        BookingReceipt receipt = facade.completeBooking(request);

        assertTrue(receipt.isSuccess());
        assertEquals(238.50, receipt.getFinalCost());
    }

    @Test
    void testValidationFailureTooShort() {
        request.setDurationMinutes(10); // Минимум для машины 240

        BookingReceipt receipt = facade.completeBooking(request);

        assertFalse(receipt.isSuccess());
        assertTrue(receipt.getMessage().contains("Error: Rental duration"));

        verify(notificationService, never()).notifySubscribers(anyString(), anyString());
    }

    @Test
    void testStrategySelectionDaily() {
        request.setDurationMinutes(1500);

        BookingReceipt receipt = facade.completeBooking(request);

        assertTrue(receipt.isSuccess());
        assertEquals(1728.00, receipt.getFinalCost());
    }

    @Test
    void testPaymentFailure() {
        try (MockedConstruction<SecureWebTransaction> mocked = mockConstruction(SecureWebTransaction.class,
                (mock, context) -> when(mock.executeTransaction(anyDouble())).thenReturn(false))) {

            BookingReceipt receipt = facade.completeBooking(request);

            assertFalse(receipt.isSuccess());
            assertEquals("Payment Error: Transaction failed.", receipt.getMessage());

            verify(notificationService, never()).notifySubscribers(anyString(), anyString());
        }
    }
}