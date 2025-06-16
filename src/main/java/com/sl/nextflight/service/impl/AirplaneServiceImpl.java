package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Airplane;
import com.sl.nextflight.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneServiceImpl {
    @Autowired
    private AirplaneRepository airplaneRepository;

    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }

    public Airplane findById(Long id) {
        return airplaneRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Airplane not found with id: " + id));
    }

    public void save(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public void deleteById(Long id) {
        airplaneRepository.deleteById(id);
    }
}
