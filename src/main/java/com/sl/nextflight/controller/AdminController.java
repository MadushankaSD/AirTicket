package com.sl.nextflight.controller;

import com.sl.nextflight.model.UserDto;
import com.sl.nextflight.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin-dashboard")
    public String adminHome(Model model) {
        return "admin-dashboard";
    }

    @GetMapping("/admin/users-management")
    public String adminUserCreate(Model model) {
        return "create-user";
    }

    @PostMapping("/admin/users/create")
    public String createUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model) {

        try {
            UserDto userDto = new UserDto();
            userDto.setRole(role);
            userDto.setUsername(username);
            userDto.setEmail(email);
            userDto.setPassword(password);

            userService.registerUser(userDto);
            model.addAttribute("success", "User created successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error creating user: " + e.getMessage());
        }

        return "create-user"; // return to the same JSP with message
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "create-user";
    }

    @GetMapping("/admin/users/search")
    public String searchUsers(@RequestParam String keyword, Model model) {
        model.addAttribute("users", userService.search(keyword));
        return "create-user";
    }

    @PostMapping("/admin/users/toggle/{id}")
    public String toggleUser(@PathVariable Long id, RedirectAttributes redirect) {
        userService.toggleEnabled(id);
        redirect.addFlashAttribute("success", "User status updated.");
        return "redirect:/admin/users";
    }



}
