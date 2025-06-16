package com.sl.nextflight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user-dashboard")
    public String adminHome(Model model) {
        return "user/user-dashboard";
    }
}
