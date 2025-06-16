package com.sl.nextflight.controller;

import com.sl.nextflight.model.FlightClass;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController {

    @GetMapping("/manager-dashboard")
    public String managerHome(Model model) {
        return "manager/manager-dashboard";
    }

    @GetMapping("/operator/bookings/new")
    public String newBooking(Model model) {
        return "admin/create-booking";
    }
    @GetMapping("manage/bookings/search")
    public String searchBooking(Model model) {
        return "manager/retrieve-bookings";
    }

    @GetMapping("/operator/reports/traffic")
    public String flightReport(Model model) {
        return "manager/flight-reports";
    }

    @GetMapping("/operator/reports/manifest")
    public String passengerManifest(Model model) {
        return "manager/passenger-manifest";
    }


}
