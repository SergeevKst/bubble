package com.generation.application.mapper.impl;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User entity) {
        return new UserReadDto(
                entity.getId(),
                entity.getLogin(),
                entity.getPhoneNumber(),
                entity.getRole().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                Optional.ofNullable(entity.getOrders()).orElseGet(HashSet::new)
                        .stream()
                        .map(Order::getId)
                        .collect(Collectors.toSet()));
    }
}
