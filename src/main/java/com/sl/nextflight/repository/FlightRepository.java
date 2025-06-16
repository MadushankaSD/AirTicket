package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("SELECT f FROM Flight f WHERE f.origin.code = :originCode AND f.destination.code = :destinationCode")
    List<Flight> findFlights(String originCode, String destinationCode, LocalDate date);
    List<Flight> findByAirplaneId(Long airplaneId);
    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> findByOriginIdAndDestinationIdAndDepartureTimeBetween(Long originId, Long destinationId, LocalDateTime start, LocalDateTime end);
    List<Flight> findByDate(LocalDate date);

    List<Flight> findByOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit(Long originId, Long destinationId, LocalDateTime start, LocalDateTime end, boolean isTransit);
}
