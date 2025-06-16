package com.sl.nextflight.entity;

import com.sl.nextflight.model.FlightClass;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "airplane_id") // Matches your DB column
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;
    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
    private LocalDate date;
    private boolean transit;

    // Seats per class
    private int economySeats;
    private int businessSeats;
    private int firstClassSeats;

    // Seats booked per class
    private int economyBooked = 0;
    private int businessBooked = 0;
    private int firstClassBooked = 0;

    public void setId(Long id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }



    public Flight() {}

    // === Getters and Setters === //

    public Long getId() {
        return id;
    }


    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }



    public boolean isTransit() {
        return transit;
    }

    public void setTransit(boolean transit) {
        this.transit = transit;
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

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public void bookSeat(FlightClass flightClass,int count) {
        switch (flightClass) {
            case ECONOMY -> {
                if (economyBooked < economySeats) economyBooked+=count;
            }
            case BUSINESS -> {
                if (businessBooked < businessSeats) businessBooked+=count;
            }
            case FIRST -> {
                if (firstClassBooked < firstClassSeats) firstClassBooked+=count;
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
