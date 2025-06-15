package com.sl.nextflight.service;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.model.FlightClass;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    public List<Flight> getAllFlights();
    public List<Flight> searchDirectFlights(String origin, String destination, LocalDateTime date, FlightClass flightClass);
//    public List<Flight> searchTransitFlights(String origin, String destination, LocalDateTime date, FlightClass flightClass);

    public boolean bookSeat(Long flightId, FlightClass flightClass);
    public Flight getFlightById(Long id);
    public boolean hasSchedulingConflict(Flight newFlight);
    public List<Flight> getFlightsByAirportAndTime(String airportCode, LocalDateTime start, LocalDateTime end);
    public List<String> getPassengerManifest(Long flightId);
}
