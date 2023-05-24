package com.generation.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.application.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findByLogin(String login);

    @Query("select u from User u left join fetch u.orders where u.id = ?1")
    User findByIdWithOrder(Integer id);
}
