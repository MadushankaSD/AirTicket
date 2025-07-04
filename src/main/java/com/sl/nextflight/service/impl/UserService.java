package com.sl.nextflight.service.impl;

import com.sl.nextflight.entity.Role;
import com.sl.nextflight.entity.User;
import com.sl.nextflight.model.UserDto;
import com.sl.nextflight.repository.RoleRepository;
import com.sl.nextflight.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Your JPA repository

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public boolean registerUser(UserDto user) {
        // Encrypt the password before saving
        try {
            String rawPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            User saveUser = new User();
            saveUser.setPassword(encodedPassword);
            saveUser.setUsername(user.getUsername());
            saveUser.setEmail(user.getEmail());

            Optional<Role> role = roleRepository.findByName(user.getRole());

            if (role.isEmpty()) {
                throw new RuntimeException("Role not found: " + user.getRole());
            }
            saveUser.setRoles(role.get());
            User save = userRepository.save(saveUser);
//            log.info("User {} registered successfully with role {}", save.getUsername(), save.getRoles().getName());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> search(String keyword) {
        Optional<List<User>> byUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase = userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
        if (byUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase.isPresent()) {
            return byUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase.get();
        } else {
            return List.of(); // Return an empty list if no users found
        }
    }

    public void toggleEnabled(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    public User findUserById(String userName , String email) {
        Optional<User> user = userRepository.findByUsernameOrEmail(userName, email);
        if (user.isPresent()) {
            return user.get();
        } else {
           return null;
        }
    }

    public boolean disableUser(Long userId){
        userRepository.findById(userId).ifPresent(user -> {
            user.setEnabled(!user.isEnabled());
            userRepository.save(user);
        });
        return true;
    }

    public User saveUser(User user){
       return userRepository.save(user);
    }

}
