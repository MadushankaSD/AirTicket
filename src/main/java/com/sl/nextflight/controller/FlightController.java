package com.sl.nextflight.controller;

import com.sl.nextflight.dto.FlightSearchResult;
import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.model.FlightClass;
import com.sl.nextflight.service.FlightService;
import com.sl.nextflight.service.impl.BookingServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class FlightController {

    private final FlightService flightsService;
    private final BookingServiceImpl bookingService;

    public FlightController(FlightService flightsService,BookingServiceImpl bookingService) {
        this.flightsService = flightsService;
        this.bookingService = bookingService;
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

        List<FlightSearchResult> results = flightsService.searchDirectFlights(origin, destination, date, classType);
        if (results.isEmpty()) {
            model.addAttribute("message", "No flights found for the selected criteria.");
        } else {
            model.addAttribute("flights", results);
            model.addAttribute("classType", classType.name());
        }
        return "home";
    }

    @GetMapping("/flights/search")
    public String searchFlightsForm(Model model) {
        return "home";
    }



    @PostMapping("/booking")
    @ResponseBody
    public ResponseEntity<?> bookFlight(@RequestParam Long flightId,
                                        @RequestParam String travelClass,
                                        @RequestParam int seatCount,
                                        @RequestParam String passengerName,
                                        @RequestParam String email,
                                        @RequestParam String mobile,
                                        HttpSession session) {
        try {
            boolean b = flightsService.createBooking(flightId,session,FlightClass.ECONOMY, seatCount,passengerName, email, mobile);
            if(b){
                return ResponseEntity.ok(Map.of("message", "Booking confirmed!"));
            }else{
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Something Wrong on Application"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/cancelBooking")
    @ResponseBody
    public ResponseEntity<String> cancelBooking(@RequestParam Long bookingId) {
        boolean success = bookingService.cancelBookingById(bookingId);
        if (success) {
            return ResponseEntity.ok("Booking cancelled");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to cancel booking");
        }
    }


}
