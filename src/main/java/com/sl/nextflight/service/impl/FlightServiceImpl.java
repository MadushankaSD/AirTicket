package com.sl.nextflight.service.impl;

import com.sl.nextflight.dto.FlightSearchResult;
import com.sl.nextflight.entity.Booking;
import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.entity.Role;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.model.FlightClass;
import com.sl.nextflight.repository.BookingRepository;
import com.sl.nextflight.repository.FlightRepository;
import com.sl.nextflight.repository.RoleRepository;
import com.sl.nextflight.service.FlightService;
import com.sl.nextflight.service.utill.CommonUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private RoleRepository roleRepository;

    public Flight saveFlight(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    @Override
    public List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date) {
        // Assuming Flight entity has origin.id, destination.id and date stored as LocalDate or LocalDateTime
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return flightRepository.findByOriginIdAndDestinationIdAndDepartureTimeBetween(originId, destinationId, startOfDay, endOfDay);
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> findByAirplaneId(Long airplaneId) {
        return flightRepository.findByAirplaneId(airplaneId);
    }

    @Override
    public Flight saveAndFlush(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    @Override
    public List<Flight> findByDate(LocalDateTime date) {
        // Find all flights on the day of this date
        LocalDate localDate = date.toLocalDate();
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
        return flightRepository.findByDepartureTimeBetween(startOfDay, endOfDay);
    }

    @Override
    public List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDateTime date) {
        // Find flights for that exact date/time (or better, same day)
        LocalDate localDate = date.toLocalDate();
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
        return flightRepository.findByOriginIdAndDestinationIdAndDepartureTimeBetween(originId, destinationId, startOfDay, endOfDay);
    }

    @Override
    public List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date, FlightClass flightClass) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Flight> byOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit = flightRepository.findByOriginIdAndDestinationIdAndDepartureTimeBetween(originId, destinationId, startOfDay, endOfDay);
        return byOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit.stream()
                .filter(flight -> flight.isSeatAvailable(flightClass))
                .collect(Collectors.toList());
    }


    @Override
    public List<Flight> findByOriginDestinationDate(Long originId, Long destinationId, LocalDate date, FlightClass flightClass, boolean isTransit) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Flight> byOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit = flightRepository.findByOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit(originId, destinationId, startOfDay, endOfDay, isTransit);
        return byOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit.stream()
                .filter(flight -> flight.isSeatAvailable(flightClass))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSeatAvailable(Long flightId, FlightClass flightClass) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) return false;
        return checkSeatAvailability(flight, flightClass);
    }

    @Override
    public boolean isSeatAvailable(Long flightId, FlightClass flightClass, boolean isTransit) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) return false;
        if (flight.isTransit() != isTransit) return false;
        return checkSeatAvailability(flight, flightClass);
    }

    @Override
    public boolean isSeatAvailable(Long flightId, FlightClass flightClass, boolean isTransit, String passengerName) {
        // You may want to check passenger bookings or reservations here; simplified to ignore passengerName for now.
        return isSeatAvailable(flightId, flightClass, isTransit);
    }


    // Helper to check seat availability on flight for class
    private boolean checkSeatAvailability(Flight flight, FlightClass flightClass) {
        switch (flightClass) {
            case ECONOMY:
                return flight.getEconomySeats() > 0;
            case BUSINESS:
                return flight.getBusinessSeats() > 0;
            case FIRST:
                return flight.getFirstClassSeats() > 0;
            default:
                return false;
        }
    }

    // 2. Get all scheduled flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // 3. Get available direct flights by origin, destination, and class
    public List<FlightSearchResult> searchDirectFlights(String origin, String destination, LocalDate date, FlightClass flightClass) {

        return flightRepository.findFlights(origin, destination, date).stream()
                .filter(f -> !f.isTransit() && f.isSeatAvailable(flightClass))
                .map(FlightServiceImpl::toDto)
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
    @Transactional
    public boolean createBooking(Long flightId, HttpSession session, FlightClass flightClass, int seatCount, String passengerName, String email, String mobile){

        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isEmpty()) {
            return false;
        }
        Flight flight = flightOpt.get();
        if (!flight.isSeatAvailable(flightClass)) {
            return false;
        }
        // Book the seat
        flight.bookSeat(flightClass, seatCount);
        flightRepository.save(flight);

        Booking booking = new Booking();
        Object user = session.getAttribute("user");
        if(user instanceof User user2) {
            booking.setUser(user2);
        }else {
            User userById = userService.findUserById(passengerName, email);
            if(userById == null){
                Role defaultRole = roleRepository.findByName("CUSTOMER").orElseThrow(() -> new RuntimeException("Default role CUSTOMER not found"));
                User user1 = new User();
                user1.setEmail(email);
                user1.setRoles(defaultRole);
                user1.setEnabled(true);
                user1.setPassword(commonUtil.generatePassword(10));
                user1.setUsername(passengerName);
                userService.saveUser(user1);
                booking.setUser(user1);
            }
            booking.setUser(userById);
        }
        booking.setFlight(flight);
        booking.setTravelClass(flightClass);
        booking.setSeatCount(seatCount);
        booking.setPassengerName(passengerName);
        booking.setEmail(email);
        booking.setMobile(mobile);
        booking.setBookingTime(LocalDateTime.now());
        bookingRepository.save(booking);
        return true;
    }


    // 6. Find flight by ID
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Flight not found"));
    }

    // 7. Check for scheduling conflicts (same airplane double-booked)
    public boolean hasSchedulingConflict(Flight newFlight) {
        List<Flight> all = flightRepository.findByAirplaneId(newFlight.getAirplane().getId());
        return all.stream().anyMatch(f ->
                !f.getId().equals(newFlight.getId()) &&
                        f.getDepartureTime().isBefore(newFlight.getArrivalTime()) &&
                        f.getArrivalTime().isAfter(newFlight.getDepartureTime())
        );
    }

    // 8. Get flights by airport within time range
    public List<Flight> getFlightsByAirportAndTime(String airportCode, LocalDateTime start, LocalDateTime end) {
        return flightRepository.findAll().stream()
                .filter(f -> (f.getOrigin().getCode().equalsIgnoreCase(airportCode) || f.getDestination().getCode().equalsIgnoreCase(airportCode))
                        && (f.getDepartureTime().isAfter(start) && f.getDepartureTime().isBefore(end)))
                .collect(Collectors.toList());
    }

    // 9. Get passenger manifest by flight
    public List<String> getPassengerManifest(Long flightId) {
        // Placeholder - depends on your Booking or Passenger repository
        return List.of("John Doe - 1A", "Jane Smith - 2C");
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public boolean hasConflict(Flight flightToCheck) {
        // Find all flights on the same date with same airplane or same origin/destination
        List<Flight> flightsOnDate = flightRepository.findByDate(flightToCheck.getDate());

        for (Flight existing : flightsOnDate) {
            if (existing.getId() != null && existing.getId().equals(flightToCheck.getId())) {
                continue; // skip self for update case
            }
            // Check airplane conflict
            if (existing.getAirplane().getId().equals(flightToCheck.getAirplane().getId())) {
                // Check overlapping times
                if (timesOverlap(existing.getDepartureTime(), existing.getArrivalTime(),
                        flightToCheck.getDepartureTime(), flightToCheck.getArrivalTime())) {
                    return true;
                }
            }
            // Optionally check origin/destination conflict logic as needed
        }
        return false;
    }

    @Override
    public boolean cancelBookingById(Long bookingId) {
        return false;
    }

    private boolean timesOverlap(LocalDateTime start1, LocalDateTime end1,
                                 LocalDateTime start2, LocalDateTime end2) {
        return !(end1.isBefore(start2) || end2.isBefore(start1));
    }


    public static FlightSearchResult toDto(Flight flight) {
        if (flight == null || flight.getAirplane() == null || flight.getOrigin() == null || flight.getDestination() == null) {
            throw new IllegalArgumentException("Invalid flight data: airplane/origin/destination missing");
        }

        return new FlightSearchResult(
                flight.getId(),
                flight.getAirplane().getManufacturer(), // Assuming Airplane has Airline relationship
                flight.getAirplane().getId().toString(),       // Or some flight number field
                flight.getOrigin().getCity() + " (" + flight.getOrigin().getCode() + ")",
                flight.getDestination().getCity() + " (" + flight.getDestination().getCode() + ")",
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getEconomySeats() - flight.getEconomyBooked(),
                flight.getBusinessSeats() - flight.getBusinessBooked(),
                flight.getFirstClassSeats() - flight.getFirstClassBooked(),
                calculateEconomyPrice(flight),
                calculateBusinessPrice(flight),
                calculateFirstClassPrice(flight),
                flight.isTransit()
        );
    }

    private static double calculateEconomyPrice(Flight flight) {
        // Add your dynamic or fixed pricing logic here
        return 150.0; // Placeholder
    }

    private static double calculateBusinessPrice(Flight flight) {
        return 300.0; // Placeholder
    }

    private static double calculateFirstClassPrice(Flight flight) {
        return 500.0; // Placeholder
    }
}
