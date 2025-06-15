package com.sl.nextflight.controller;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightsService;

    public FlightController(FlightService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping("/searchFlights")
    public String searchFlights(
            @RequestParam String fromCity,
            @RequestParam String toCity,
            @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
//            @RequestParam(required = false) String returnDate,
//            @RequestParam(defaultValue = "1") int passengers,
            Model model) {


        List<Flight> flights = flightsService.getAvailableFlights();

        model.addAttribute("flightList", flights);
        model.addAttribute("from", fromCity);
        model.addAttribute("to", toCity);
        model.addAttribute("departureDate", departureDate);
//        model.addAttribute("returnDate", returnDate);
//        model.addAttribute("passengers", passengers);

        return "flight-results";
    }

}
