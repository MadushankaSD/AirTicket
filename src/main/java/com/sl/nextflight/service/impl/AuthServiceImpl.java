package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.User;
import com.sl.nextflight.model.UserDto;
import com.sl.nextflight.repository.UserRepository;
import com.sl.nextflight.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto validate(String username, String password, HttpSession session) {
    Optional<User> byUsername = userRepository.findByUsername(username);
    if (byUsername.isEmpty()) {
        UserDto userDto = new UserDto();
        userDto.setValid(false);
        return userDto;
    }
        User user = byUsername.get();
        session.setAttribute("user_row",user);
        UserDto userDto = new UserDto();
        userDto.setValid(passwordEncoder.matches(password, user.getPassword()));
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles().getName());

        return userDto;
    }
}
