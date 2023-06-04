package com.generation.application.service;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
import com.generation.application.model.Role;

import java.math.BigDecimal;
import java.util.Set;

public interface UserService {
    User findByLogin(String login);

    UserReadDto findUserByLogin(String login);

    UserReadDto save(User user);

    UserReadDto findByIdWithOrder(Integer id);

    UserReadDto setOrderToEmployee(String login, Integer idOrder);

    UserReadDto removeOrderFromEmployee(String login, Integer idOrder);

    Set<UserReadDto> findAllEmployee();

    User findByRole(Role role);

    UserReadDto findById(Integer userId);

    void updateUserBalance(Integer userId, BigDecimal balance);
}
