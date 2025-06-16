package com.sl.nextflight.controller;

import com.sl.nextflight.dto.FlightSearchResult;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.model.FlightClass;
import com.sl.nextflight.model.UserDto;
import com.sl.nextflight.service.FlightService;
import com.sl.nextflight.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin-dashboard")
    public String adminHome() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/users-management")
    public String adminUserCreate(Model model) {
        List<User> allUsers = userService.getAll();
        model.addAttribute("users",allUsers);
        return "admin/create-user";
    }

    @PostMapping("/users/save")
    public String createUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String roleId,
            Model model) {

        try {
            UserDto userDto = new UserDto();
            userDto.setRole(roleId);
            userDto.setUsername(username);
            userDto.setEmail(email);
            userDto.setPassword(password);

            userService.registerUser(userDto);
            model.addAttribute("success", "User created successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error creating user: " + e.getMessage());
        }

        return "redirect:/admin/users-management"; // return to the same JSP with message
    }


    @GetMapping("/users/search")
    public String searchUsers(@RequestParam String keyword, Model model) {
        model.addAttribute("users", userService.search(keyword));
        return "redirect:/admin/users-management";
    }

    @PostMapping("/users/toggle/{id}")
    public String toggleUser(@PathVariable Long id, RedirectAttributes redirect) {
        userService.toggleEnabled(id);
        redirect.addFlashAttribute("success", "User status updated.");
        return "redirect:/admin/users-management";
    }

    @GetMapping("/reports/operations")
    public String operationalReport(Model model) {
        return "/admin/operational-reports";
    }

}
