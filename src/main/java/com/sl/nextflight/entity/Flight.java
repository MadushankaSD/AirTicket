package com.sl.nextflight.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Flight {
    private String id;
    private String airline;
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private String departureAirport;
    private String arrivalAirport;
    private String duration; // e.g. "3h 25m"
    private double price;

    public Flight(String f001, String deltaAirlines, String dl123, java.util.Date dep1, java.util.Date arr1, String jfk, String lax, String s, double v) {
        this.id = f001;
        this.airline = deltaAirlines;
        this.flightNumber = dl123;
        this.departureTime = new Date(dep1.getTime());
        this.arrivalTime = new Date(arr1.getTime());
        this.departureAirport = jfk;
        this.arrivalAirport = lax;
        this.duration = s;
        this.price = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
