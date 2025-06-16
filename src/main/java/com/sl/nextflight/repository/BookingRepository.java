package com.sl.nextflight.repository;

import com.sl.nextflight.entity.Booking;
import com.sl.nextflight.entity.Flight;
import com.sl.nextflight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long> {

    List<Booking> findByUser(User user);
    @Query("SELECT b FROM Booking b WHERE b.id = ?1")
    Booking findById2(Long id);
    List<Booking>  findByPassengerNameContainingIgnoreCase(String customerName);
    List<Booking>  findByEmailContainingIgnoreCase(String email);

    @Query("SELECT b FROM Booking b")
    List<Booking>  findAllBooking();
}
