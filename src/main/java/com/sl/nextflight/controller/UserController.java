package com.sl.nextflight.controller;

import com.sl.nextflight.entity.Booking;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.service.impl.BookingServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping("/user-dashboard")
    public String adminHome(Model model) {
        return "user/user-dashboard";
    }


    @GetMapping("/my-bookings")
    public String showUserBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user_row");
        List<Booking> bookings = bookingService.findByUser(user);
        model.addAttribute("bookings", bookings);
        return "user/manage-booking";
    }

    @GetMapping("/profile")
    public String showUserProfile() {
        return "user/user-profile";
    }

    @GetMapping("/searchBookings")
    public String searchBookings(@RequestParam(required = false) Long bookingId,
                                 @RequestParam(required = false) String customerName,
                                 @RequestParam(required = false) String email,
                                 Model model) {
        List<Booking> bookings = bookingService.searchBookings(bookingId, customerName, email);
        model.addAttribute("bookings", bookings);
        return "manager/retrieve-bookings"; // Adjust based on your folder structure
    }
}
