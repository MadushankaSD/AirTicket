package com.sl.nextflight.service.impl;

import com.sl.nextflight.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean validate(String username, String password) {
        return true;
    }
}
