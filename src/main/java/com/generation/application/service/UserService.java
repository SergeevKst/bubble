package com.generation.application.service;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
public interface UserService {
    User findByLogin(String login);
    UserReadDto findUserByLogin(String login);
    UserReadDto save(User user);
    UserReadDto findByIdWithOrder(Integer id);
    UserReadDto setOrderToEmployee(String login, Integer idOrder);
    UserReadDto removeOrderFromEmployee(String login, Integer idOrder);
}
