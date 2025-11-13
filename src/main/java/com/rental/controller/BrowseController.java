package com.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrowseController {

    @GetMapping("/browse")
    public String browseVehicles() {
        return "browse-vehicles";
    }
}