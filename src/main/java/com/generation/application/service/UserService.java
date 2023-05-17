package com.generation.application.service;
import com.generation.application.entity.User;
public interface UserService {
    User findByLogin(String login);
    void save(User user);
}
