package com.sl.nextflight.entity;

import com.sl.nextflight.model.FlightClass;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who made the booking
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Flight that was booked
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "travel_class", nullable = false)
    private FlightClass travelClass;

    @Column(name = "seat_count", nullable = false)
    private int seatCount;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    @Column(name = "is_canceled", nullable = false)
    private Boolean active = false;

    public Booking() {
    }

    public Booking(User user, Flight flight, FlightClass travelClass, int seatCount,
                   String passengerName, String email, String mobile, LocalDateTime bookingTime) {
        this.user = user;
        this.flight = flight;
        this.travelClass = travelClass;
        this.seatCount = seatCount;
        this.passengerName = passengerName;
        this.email = email;
        this.mobile = mobile;
        this.bookingTime = bookingTime;
    }

    // === Getters and Setters === //

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public FlightClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(FlightClass travelClass) {
        this.travelClass = travelClass;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
