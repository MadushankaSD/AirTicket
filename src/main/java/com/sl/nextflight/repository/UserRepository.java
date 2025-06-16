package com.sl.nextflight.repository;

import com.sl.nextflight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username,String email);

    Optional<List<User>> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);
}
