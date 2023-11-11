package com.example.test.app.repository;

import com.example.test.app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
    Boolean existsByName(String name);

}
