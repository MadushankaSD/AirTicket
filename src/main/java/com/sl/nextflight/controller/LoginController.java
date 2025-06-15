package com.sl.nextflight.controller;

import com.sl.nextflight.model.User;
import com.sl.nextflight.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    private final AuthService authService;
    private final Map<String, String> tokenStore = new ConcurrentHashMap<>();


    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Logged out successfully");
        return response;
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
            Integer duration = (int) session.getAttribute("duration");
            if(System.currentTimeMillis() - lockTime < duration) {
                long l = duration - (System.currentTimeMillis() - lockTime);
                response.put("success", false);
                response.put("message", "Account is locked. Try again in "+ l/1000 +" seconds.");
                return response;
            }
        }

        boolean isValid = authService.validate(username, password);

        if (isValid) {
            String token = UUID.randomUUID().toString();
            tokenStore.put(token, username);
            session.setAttribute("authToken", token); // Store in session
            User user = new User();
            user.setRole("MANAGER");
//            user.setRole("USER");
//            user.setRole("ADMIN");
            user.setUsername(username);
            user.setName("Madushanka");

            session.removeAttribute("loginAttempts");
            session.removeAttribute("lockTime");
            session.setAttribute("user",user);
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

    public boolean isValidToken(HttpSession session) {
        String token = (String) session.getAttribute("authToken");
        return token != null && tokenStore.containsKey(token);
    }
}
