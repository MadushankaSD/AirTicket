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
}
