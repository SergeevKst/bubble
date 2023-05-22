package com.generation.application.mapper.impl;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User entity) {
        return new UserReadDto(entity.getId(),
                entity.getLogin(),
                entity.getPhoneNumber(),
                entity.getRole().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getOrders()
                        .stream()
                        .map(Order::getId)
                        .collect(Collectors.toSet()));
    }
}
