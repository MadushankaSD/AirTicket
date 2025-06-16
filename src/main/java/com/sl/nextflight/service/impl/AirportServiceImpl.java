package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.repository.AirportRepository;
import com.sl.nextflight.repository.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AirportServiceImpl {
    @Autowired
    private AirportRepository airportRepository;

    public void save(Airport airport) {
        if (airport.getCode() == null || airport.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Airport code cannot be null or empty.");
        }
        airportRepository.save(airport);
    }

    public Airport findById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Airport not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Airport with ID " + id + " does not exist.");
        }
    }

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }
}
