package com.sl.nextflight.controller;

import com.sl.nextflight.entity.City;
import com.sl.nextflight.service.CityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomePageController {

    private final CityService cityService;

    public HomePageController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = {"/home", "/"})
    public String home(Model model) {
        List<City> cities = cityService.getCity();
        model.addAttribute("cities", cities);
        return "home";
    }

    @GetMapping("/logout")
    public String logoutManagement(HttpSession session) {
        session.invalidate();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Logged out successfully");
        return "redirect:/home";
    }


}
