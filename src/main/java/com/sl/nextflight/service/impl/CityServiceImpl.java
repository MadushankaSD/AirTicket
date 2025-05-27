package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.City;
import com.sl.nextflight.service.CityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    public List<City> getCity(){
        return List.of(
                new City(1L, "New York"),
                new City(2L, "Los Angeles"),
                new City(3L, "Chicago"),
                new City(4L, "Houston"),
                new City(5L, "Phoenix")
        );
    }
}
