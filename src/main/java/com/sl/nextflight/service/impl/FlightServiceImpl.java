package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.model.FlightClass;
import com.sl.nextflight.repository.FlightRepository;
import com.sl.nextflight.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight saveFlight(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    // 2. Get all scheduled flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // 3. Get available direct flights by origin, destination, and class
    public List<Flight> searchDirectFlights(String origin, String destination, LocalDateTime date, FlightClass flightClass) {
        java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(date);

        return flightRepository.findByOriginAndDestinationAndDate(origin, destination, sqlTimestamp).stream()
                .filter(f -> !f.isTransit() && f.isSeatAvailable(flightClass))
                .collect(Collectors.toList());
    }

    // 4. Get available transit flights (origin to connecting to destination)
//    public List<Flight> searchTransitFlights(String origin, String destination, LocalDateTime date, FlightClass flightClass) {
//        List<Flight> allFlights = flightRepository.findByDate(date);
//
//        return allFlights.stream()
//                .filter(f1 -> f1.getOrigin().equalsIgnoreCase(origin)
//                                && f1.isSeatAvailable(flightClass)
//                                && f1.isTransit()
//                                && allFlights.stream().anyMatch(f2 ->
//                                f2.getOrigin().equalsIgnoreCase(f1.getDestination())
//                                        && f2.getDestination().equalsIgnoreCase(destination)
//                                        && f2.isSeatAvailable(flightClass)
//                                        && f2.getDepartureTime().isAfter(f1.getArrivalTime().plusMinutes(30))
//                        )
//                )
//                .collect(Collectors.toList());
//    }

    // 5. Book a seat
    public boolean bookSeat(Long flightId, FlightClass flightClass) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.isSeatAvailable(flightClass)) {
                flight.bookSeat(flightClass);
                flightRepository.saveAndFlush(flight);
                return true;
            }
        }
        return false;
    }

    // 6. Find flight by ID
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // 7. Check for scheduling conflicts (same airplane double-booked)
    public boolean hasSchedulingConflict(Flight newFlight) {
        List<Flight> all = flightRepository.findByAirplaneId(newFlight.getAirplaneId());
        return all.stream().anyMatch(f ->
                !f.getId().equals(newFlight.getId()) &&
                        f.getDepartureTime().isBefore(newFlight.getArrivalTime()) &&
                        f.getArrivalTime().isAfter(newFlight.getDepartureTime())
        );
    }

    // 8. Get flights by airport within time range
    public List<Flight> getFlightsByAirportAndTime(String airportCode, LocalDateTime start, LocalDateTime end) {
        return flightRepository.findAll().stream()
                .filter(f -> (f.getOrigin().equalsIgnoreCase(airportCode) || f.getDestination().equalsIgnoreCase(airportCode))
                        && (f.getDepartureTime().isAfter(start) && f.getDepartureTime().isBefore(end)))
                .collect(Collectors.toList());
    }

    // 9. Get passenger manifest by flight
    public List<String> getPassengerManifest(Long flightId) {
        // Placeholder - depends on your Booking or Passenger repository
        return List.of("John Doe - 1A", "Jane Smith - 2C");
    }
}
