package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Booking;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.repository.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BookingServiceImpl {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findByUser(User user){
        return bookingRepository.findByUser(user);
    }

    public boolean cancelBookingById(Long bookingId){
        Optional<Booking> byId = bookingRepository.findById(bookingId);
        if (byId.isPresent()) {
            Booking booking = byId.get();
            booking.setActive(false);
            bookingRepository.save(booking);
            return true;
        } else {
            return false; // Booking not found
        }
    }

}
