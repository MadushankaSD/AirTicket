package com.sl.nextflight.controller;

import com.sl.nextflight.entity.City;
import com.sl.nextflight.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
