package com.sl.nextflight.controller;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.service.FlightService;
import com.sl.nextflight.service.impl.AirplaneServiceImpl;
import com.sl.nextflight.service.impl.AirportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/manage/flights")
public class FlightScheduleController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AirplaneServiceImpl airplaneService;

    @Autowired
    private AirportServiceImpl airportService;

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("airports", airportService.findAll());
        return "admin/flight-schedule";
    }

    @GetMapping("/search")
    public String searchFlights(@RequestParam Long originId,
                                @RequestParam Long destinationId,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                Model model) {
        model.addAttribute("flights", flightService.findByOriginDestinationDate(originId, destinationId, date));
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("airports", airportService.findAll());
        return "flight-schedule";
    }

    @GetMapping("/edit/{id}")
    public String editFlight(@PathVariable Long id, Model model) {
        Flight flightById = flightService.getFlightById(id);
        model.addAttribute("flightToEdit", flightById);
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("airplanes", airplaneService.findAll());
        model.addAttribute("airports", airportService.findAll());
        return "flight-schedule";
    }

    @PostMapping("/create")
    public String createFlight(@RequestParam Long airplaneId,
                               @RequestParam Long originId,
                               @RequestParam Long destinationId,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime departureTime,
                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime arrivalTime,
                               @RequestParam(required = false, defaultValue = "false") boolean transit,
                               @RequestParam int economySeats,
                               @RequestParam int businessSeats,
                               @RequestParam int firstClassSeats,
                               RedirectAttributes redirectAttrs) {

        // Compose timestamps combining date+time
        LocalDateTime depDateTime = LocalDateTime.of(date, departureTime);
        LocalDateTime arrDateTime = LocalDateTime.of(date, arrivalTime);

        Flight flight = new Flight();
        flight.setAirplane(airplaneService.findById(airplaneId));
        flight.setOrigin(airportService.findById(originId));
        flight.setDestination(airportService.findById(destinationId));
        flight.setDate(depDateTime);
        flight.setDepartureTime(depDateTime);
        flight.setArrivalTime(arrDateTime);
        flight.setTransit(transit);
        flight.setEconomySeats(economySeats);
        flight.setBusinessSeats(businessSeats);
        flight.setFirstClassSeats(firstClassSeats);

        // Check for conflicts (implement in service)
        if (flightService.hasConflict(flight)) {
            redirectAttrs.addFlashAttribute("errorMessage", "Flight conflicts with existing schedule!");
            return "redirect:/manage/flights";
        }

        flightService.saveFlight(flight);
        return "redirect:/manage/flights";
    }

    @PostMapping("/update/{id}")
    public String updateFlight(@PathVariable Long id,
                               @RequestParam Long airplaneId,
                               @RequestParam Long originId,
                               @RequestParam Long destinationId,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime departureTime,
                               @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime arrivalTime,
                               @RequestParam(required = false, defaultValue = "false") boolean transit,
                               @RequestParam int economySeats,
                               @RequestParam int businessSeats,
                               @RequestParam int firstClassSeats,
                               RedirectAttributes redirectAttrs) {

        Flight flight = flightService.findById(id);

        LocalDateTime depDateTime = LocalDateTime.of(date, departureTime);
        LocalDateTime arrDateTime = LocalDateTime.of(date, arrivalTime);

        flight.setAirplane(airplaneService.findById(airplaneId));
        flight.setOrigin(airportService.findById(originId));
        flight.setDestination(airportService.findById(destinationId));
        flight.setDate(depDateTime);
        flight.setDepartureTime(depDateTime);
        flight.setArrivalTime(arrDateTime);
        flight.setTransit(transit);
        flight.setEconomySeats(economySeats);
        flight.setBusinessSeats(businessSeats);
        flight.setFirstClassSeats(firstClassSeats);

        if (flightService.hasConflict(flight)) {
            redirectAttrs.addFlashAttribute("errorMessage", "Flight conflicts with existing schedule!");
            return "redirect:/manage/flights/edit/" + id;
        }

        flightService.saveFlight(flight);
        return "redirect:/manage/flights";
    }

    @PostMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteById(id);
        return "redirect:/manage/flights";
    }

}
