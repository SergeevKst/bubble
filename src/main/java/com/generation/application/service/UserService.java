package com.generation.application.service;
import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
public interface UserService {
    User findByLogin(String login);
    UserReadDto findUserByLogin(String login);
    void save(User user);
    UserReadDto findByIdWithOrder(Integer id);
    void setOrderToEmployee(String login, Integer idOrder);
    void removeOrderFromEmployee(String login, Integer idOrder);
}
