package com.sl.nextflight.controller;

import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.entity.City;
import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.service.CityService;
import com.sl.nextflight.service.FlightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomePageController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = {"/home", "/"})
    public String home(Model model) {
        List<Flight> flights = flightService.findAll();
        // Extract distinct origin city names
        Map<String, String> originCities = flights.stream()
                .map(Flight::getOrigin)
                .filter(airport -> airport.getCity() != null && airport.getCode() != null)
                .collect(Collectors.toMap(
                        Airport::getCity,
                        Airport::getCode,
                        (existing, replacement) -> existing, // handle duplicates
                        TreeMap::new // keep it sorted by city
                )); // TreeSet sorts the city names

        Map<String, String> destinationCities = flights.stream()
                .map(Flight::getDestination)
                .filter(airport -> airport.getCity() != null && airport.getCode() != null)
                .collect(Collectors.toMap(
                        Airport::getCity,
                        Airport::getCode,
                        (existing, replacement) -> existing, // handle duplicates
                        TreeMap::new // keep it sorted by city
                ));


        model.addAttribute("originCities", originCities);
        model.addAttribute("destinationCities", destinationCities);
        return "home";
    }

    @GetMapping("/logout")
    public String logoutManagement(HttpSession session) {
        session.invalidate();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Logged out successfully");
        return "redirect:/home";
    }


}
