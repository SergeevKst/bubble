package com.generation.application.repository;

import com.generation.application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.application.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findByLogin(String login);

    @Query("select u from User u left join fetch u.orders where u.id = ?1")
    User findByIdWithOrder(Integer id);

    @Query("select u from User u where u.role !=?1 ")
    Set<User> findAllEployee(Role role);

}
