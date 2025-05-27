package com.sl.nextflight.service;

import com.sl.nextflight.entity.City;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface CityService {
    List<City> getCity();

}
