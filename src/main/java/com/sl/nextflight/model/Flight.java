package com.sl.nextflight.model;

import java.time.LocalDateTime;

public class Flight {

    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private boolean isTransit;

    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;

    private int seatsEconomy;
    private int seatsBusiness;
    private int seatsFirst;

    // Getters and Setters

    public boolean isSeatAvailable(FlightClass flightClass) {
        return switch (flightClass) {
            case ECONOMY -> seatsEconomy > 0;
            case BUSINESS -> seatsBusiness > 0;
            case FIRST -> seatsFirst > 0;
        };
    }

    public double getPriceByClass(FlightClass flightClass) {
        return switch (flightClass) {
            case ECONOMY -> priceEconomy;
            case BUSINESS -> priceBusiness;
            case FIRST -> priceFirst;
        };
    }

    public void bookSeat(FlightClass flightClass) {
        switch (flightClass) {
            case ECONOMY -> seatsEconomy--;
            case BUSINESS -> seatsBusiness--;
            case FIRST -> seatsFirst--;
        }
    }
}
