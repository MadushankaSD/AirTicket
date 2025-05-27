package com.sl.nextflight.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean validate(String username,String password);
}
