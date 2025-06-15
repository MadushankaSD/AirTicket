package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByOriginAndDestinationAndDate(String origin, String destination, Timestamp date);
    List<Flight> findByAirplaneId(Long airplaneId);

}
