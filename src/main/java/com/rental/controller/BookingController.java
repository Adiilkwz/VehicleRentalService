package com.rental.controller;

import com.rental.model.BookingReceipt;
import com.rental.model.RentalRequestDTO;
import com.rental.person1.facade.WebRentalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    private final WebRentalFacade webRentalFacade;

    @Autowired
    public BookingController(WebRentalFacade webRentalFacade) {
        this.webRentalFacade = webRentalFacade;
    }

    @GetMapping("/booking")
    public String showBookingForm(@RequestParam(name = "type", required = false, defaultValue = "Car") String vehicleType, Model model) {

        RentalRequestDTO request = new RentalRequestDTO();
        request.setVehicleType(vehicleType);

        model.addAttribute("rentalRequest", request);
        model.addAttribute("vehicleType", vehicleType);

        return "booking-form";
    }

    @PostMapping("/confirm-booking")
    public String confirmBooking(@ModelAttribute("rentalRequest") RentalRequestDTO request, Model model) {

        BookingReceipt receipt = webRentalFacade.completeBooking(request);

        model.addAttribute("receipt", receipt);

        return "confirmation";
    }
}