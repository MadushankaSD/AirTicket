package com.sl.nextflight.controller;

import com.sl.nextflight.entity.Airplane;
import com.sl.nextflight.entity.Airport;
import com.sl.nextflight.service.impl.AirplaneServiceImpl;
import com.sl.nextflight.service.impl.AirportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AirportAirCraftController {

    @Autowired
    private AirplaneServiceImpl airplaneService;
    @Autowired
    private AirportServiceImpl airportService;

    @GetMapping("/manage/aircraft")
    public String aircraft(Model model) {
        model.addAttribute("airplanes", airplaneService.findAll());
        return "admin/aircraft-manage";
    }

    @GetMapping("/manage/airport")
    public String airport(Model model) {
        model.addAttribute("airports", airportService.findAll());
        return "admin/airport-manage";
    }

    // POST: Handle form submission to create new airplane
    @PostMapping("/manage/airplanes/create")
    public String createAirplane(@RequestParam("model") String modelName,
                                 @RequestParam("manufacturer") String manufacturer,
                                 @RequestParam("seatCapacity") int seatCapacity) {

        Airplane airplane = new Airplane();
        airplane.setModel(modelName);
        airplane.setManufacturer(manufacturer);
        airplane.setSeatCapacity(seatCapacity);

        airplaneService.save(airplane);

        return "redirect:/manage/aircraft";
    }

    // POST: Delete airplane by ID
    @PostMapping("/manage/airplanes/delete/{id}")
    public String deleteAirplane(@PathVariable("id") Long id) {
        airplaneService.deleteById(id);
        return "redirect:/manage/aircraft";
    }

    // Create a new airport
    @PostMapping("/manage/airports/create")
    public String createAirport(@RequestParam("code") String code,
                                @RequestParam("name") String name,
                                @RequestParam("city") String city,
                                @RequestParam("country") String country) {
        Airport airport = new Airport();
        airport.setCode(code);
        airport.setName(name);
        airport.setCity(city);
        airport.setCountry(country);

        airportService.save(airport);
        return "redirect:/manage/airport";
    }

    // Optional: delete airport
    @PostMapping("/delete/{id}")
    public String deleteAirport(@PathVariable("id") Long id) {
        airportService.deleteById(id);
        return "redirect:/manage/airport";
    }
}
