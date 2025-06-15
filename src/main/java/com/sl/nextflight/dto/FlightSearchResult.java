package com.sl.nextflight.dto;

import java.time.LocalDateTime;

public class FlightSearchResult {
    private Long flightId;
    private String airline;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableEconomySeats;
    private int availableBusinessSeats;
    private int availableFirstClassSeats;
    private double economyPrice;
    private double businessPrice;
    private double firstClassPrice;
    private boolean isTransit; // true if it's a connecting/transit flight

    // --- Constructors ---
    public FlightSearchResult() {}

    public FlightSearchResult(Long flightId, String airline, String flightNumber, String origin, String destination,
                              LocalDateTime departureTime, LocalDateTime arrivalTime,
                              int availableEconomySeats, int availableBusinessSeats, int availableFirstClassSeats,
                              double economyPrice, double businessPrice, double firstClassPrice,
                              boolean isTransit) {
        this.flightId = flightId;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableEconomySeats = availableEconomySeats;
        this.availableBusinessSeats = availableBusinessSeats;
        this.availableFirstClassSeats = availableFirstClassSeats;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.firstClassPrice = firstClassPrice;
        this.isTransit = isTransit;
    }

    // --- Getters and Setters ---

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getAvailableEconomySeats() { return availableEconomySeats; }
    public void setAvailableEconomySeats(int availableEconomySeats) { this.availableEconomySeats = availableEconomySeats; }

    public int getAvailableBusinessSeats() { return availableBusinessSeats; }
    public void setAvailableBusinessSeats(int availableBusinessSeats) { this.availableBusinessSeats = availableBusinessSeats; }

    public int getAvailableFirstClassSeats() { return availableFirstClassSeats; }
    public void setAvailableFirstClassSeats(int availableFirstClassSeats) { this.availableFirstClassSeats = availableFirstClassSeats; }

    public double getEconomyPrice() { return economyPrice; }
    public void setEconomyPrice(double economyPrice) { this.economyPrice = economyPrice; }

    public double getBusinessPrice() { return businessPrice; }
    public void setBusinessPrice(double businessPrice) { this.businessPrice = businessPrice; }

    public double getFirstClassPrice() { return firstClassPrice; }
    public void setFirstClassPrice(double firstClassPrice) { this.firstClassPrice = firstClassPrice; }

    public boolean isTransit() { return isTransit; }
    public void setTransit(boolean isTransit) { this.isTransit = isTransit; }
}
