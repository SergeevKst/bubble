package com.generation.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.application.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findByLogin(String login);
}
