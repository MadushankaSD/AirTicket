package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findByOrigin_CodeAndDestination_CodeAndDate(String originId, String destinationId, Timestamp date);
    List<Flight> findByAirplaneId(Long airplaneId);

    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> findByOriginIdAndDestinationIdAndDepartureTimeBetween(Long originId, Long destinationId, LocalDateTime start, LocalDateTime end);
    List<Flight> findByDate(LocalDateTime date);

    List<Flight> findByOriginIdAndDestinationIdAndDepartureTimeBetweenAndTransit(Long originId, Long destinationId, LocalDateTime start, LocalDateTime end, boolean isTransit);
}
