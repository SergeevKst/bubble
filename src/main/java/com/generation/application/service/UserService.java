package com.generation.application.service;
import com.generation.application.model.User;
public interface UserService {
    User findByLogin(String login);
    void save(User user);
}
