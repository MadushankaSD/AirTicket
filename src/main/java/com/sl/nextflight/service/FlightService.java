package com.sl.nextflight.service;

import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.model.FlightClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    List<Flight> findAll();
    Flight saveFlight(Flight flight);

    public List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date);
    Flight findById(Long id);
    void deleteById(Long id);
    List<Flight> findByAirplaneId(Long airplaneId);
    Flight saveAndFlush(Flight flight);
    List<Flight> findByDate(LocalDateTime date);
    List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDateTime date);
    List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date, FlightClass flightClass);
    List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date, FlightClass flightClass, boolean isTransit);
    boolean isSeatAvailable(Long flightId, FlightClass flightClass);
    boolean isSeatAvailable(Long flightId, FlightClass flightClass, boolean isTransit);
    boolean isSeatAvailable(Long flightId, FlightClass flightClass, boolean isTransit, String passengerName);

    public boolean hasConflict(Flight flightToCheck);

}
