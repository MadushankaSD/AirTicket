package com.sl.nextflight.service;

import com.sl.nextflight.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    List<Flight> getAvailableFlights();
}
