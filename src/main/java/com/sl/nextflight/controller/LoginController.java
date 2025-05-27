package com.sl.nextflight.controller;

import com.sl.nextflight.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password,
                                     HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        // Check lock
        Long lockTime = (Long) session.getAttribute("lockTime");
        if (lockTime != null ) {
            Long duration = (Long) session.getAttribute("duration");
            if(duration != null && System.currentTimeMillis() - lockTime < duration) {
                response.put("success", false);
                response.put("message", "Account is locked. Try again in 30 seconds.");
                return response;
            }
        }

        boolean isValid = authService.validate(username, password);

        if (isValid) {
            session.removeAttribute("loginAttempts");
            session.removeAttribute("lockTime");
            response.put("success", true);
        } else {
            Integer attempts = (Integer) session.getAttribute("loginAttempts");
            attempts = (attempts == null) ? 1 : attempts + 1;
            session.setAttribute("loginAttempts", attempts);

            if(attempts > 3){
            switch (attempts){
                case 4 -> {
                    session.setAttribute("lockTime", System.currentTimeMillis());
                    session.setAttribute("duration", 60 * 1000);
                    response.put("success", false);
                    response.put("message", "Too many failed attempts. Locked for 60 seconds.");
                }
                case 5 -> {
                    session.setAttribute("lockTime", System.currentTimeMillis());
                    session.setAttribute("duration", 5 * 60 * 1000);
                    response.put("success", false);
                    response.put("message", "Too many failed attempts. Locked for 5 minutes.");
                }
                default -> {
                    response.put("success", false);
                    session.setAttribute("lockTime", System.currentTimeMillis());
                    session.setAttribute("duration", 60 * 60 * 1000);
                    response.put("message", "Too many failed attempts. Locked for 1 hour.");
                }
            }
            }else {
                response.put("success", false);
                response.put("message", "Invalid credentials. Attempts: " + attempts);
            }
        }

        return response;
    }
}
