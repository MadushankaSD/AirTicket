package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Airplane;
import com.sl.nextflight.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
}
