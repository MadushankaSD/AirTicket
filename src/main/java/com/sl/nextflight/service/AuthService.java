package com.sl.nextflight.service;

import com.sl.nextflight.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto validate(String username, String password, HttpSession session);
}
