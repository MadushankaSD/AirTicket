package com.sl.nextflight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {
    @GetMapping("/manager-dashboard")
    public String managerHome(Model model) {
        return "manager-dashboard";
    }
}
