package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {
}
