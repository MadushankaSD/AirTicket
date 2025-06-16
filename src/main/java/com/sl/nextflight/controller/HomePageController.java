package com.sl.nextflight.controller;

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
        Set<String> originCities = flights.stream()
                .map(flight -> flight.getOrigin().getCity())
                .filter(city -> city != null && !city.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));  // TreeSet sorts the city names

        // Extract distinct destination city names
        Set<String> destinationCities = flights.stream()
                .map(flight -> flight.getDestination().getCity())
                .filter(city -> city != null && !city.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));

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
