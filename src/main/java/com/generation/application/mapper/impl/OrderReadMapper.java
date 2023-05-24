package com.generation.application.mapper.impl;

import com.generation.application.dto.OrderReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.Mapper;
import com.generation.application.mapper.OrderDetailsReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final OrderDetailsReadMapper orderDetailsReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        return new OrderReadDto(object.getId(),
                object.getUsers()
                        .stream()
                        .collect(Collectors.toMap(
                                User::getLogin, User::getRole)
                        ),
                orderDetailsReadMapper.map(object.getOrderDetails()));
    }
}
