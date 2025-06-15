package com.sl.nextflight.entity;

import com.sl.nextflight.model.FlightClass;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private String origin;

    private String destination;

    private Timestamp departureTime;

    private Timestamp arrivalTime;
    private Timestamp date;

    private boolean transit;

    private Long airplaneId;

    // Seats per class
    private int economySeats;
    private int businessSeats;
    private int firstClassSeats;

    // Seats booked per class
    private int economyBooked = 0;
    private int businessBooked = 0;
    private int firstClassBooked = 0;

    public Flight() {}

    // === Getters and Setters === //

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime.toLocalDateTime();
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = java.sql.Timestamp.valueOf(departureTime);
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime.toLocalDateTime();
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = java.sql.Timestamp.valueOf(arrivalTime);
    }

    public boolean isTransit() {
        return transit;
    }

    public void setTransit(boolean transit) {
        this.transit = transit;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public void setEconomySeats(int economySeats) {
        this.economySeats = economySeats;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(int businessSeats) {
        this.businessSeats = businessSeats;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public int getEconomyBooked() {
        return economyBooked;
    }

    public void setEconomyBooked(int economyBooked) {
        this.economyBooked = economyBooked;
    }

    public int getBusinessBooked() {
        return businessBooked;
    }

    public void setBusinessBooked(int businessBooked) {
        this.businessBooked = businessBooked;
    }

    public int getFirstClassBooked() {
        return firstClassBooked;
    }

    public void setFirstClassBooked(int firstClassBooked) {
        this.firstClassBooked = firstClassBooked;
    }

    // === Business Logic === //

    public boolean isSeatAvailable(FlightClass flightClass) {
        return switch (flightClass) {
            case ECONOMY -> economyBooked < economySeats;
            case BUSINESS -> businessBooked < businessSeats;
            case FIRST -> firstClassBooked < firstClassSeats;
        };
    }

    public void bookSeat(FlightClass flightClass) {
        switch (flightClass) {
            case ECONOMY -> {
                if (economyBooked < economySeats) economyBooked++;
            }
            case BUSINESS -> {
                if (businessBooked < businessSeats) businessBooked++;
            }
            case FIRST -> {
                if (firstClassBooked < firstClassSeats) firstClassBooked++;
            }
        }
    }
    public LocalDateTime getDate() {
        return date.toLocalDateTime();
    }
    public void setDate(LocalDateTime date) {
        this.date = java.sql.Timestamp.valueOf(date);;
    }
}
