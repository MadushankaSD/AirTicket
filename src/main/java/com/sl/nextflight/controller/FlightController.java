package com.sl.nextflight.controller;

import com.sl.nextflight.dto.FlightSearchResult;
import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.model.FlightClass;
import com.sl.nextflight.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightsService;

    public FlightController(FlightService flightsService) {
        this.flightsService = flightsService;
    }


    @GetMapping("/searchFlights")
    public String searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam(required = false) String departureDate,
            @RequestParam(defaultValue = "ECONOMY") FlightClass classType,
            Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(departureDate, formatter);
        LocalTime time = LocalTime.MIDNIGHT; // 00:00
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        List<Flight> results = flightsService.searchDirectFlights(origin, destination, dateTime, classType);
        if (results.isEmpty()) {
            model.addAttribute("message", "No flights found for the selected criteria.");
        } else {
            model.addAttribute("flights", results);
        }

        return "search-flights"; // maps to search-flights.jsp
    }

    @GetMapping("/flights/search")
    public String searchFlightsForm(Model model) {
        return "search-flights";
    }

}
