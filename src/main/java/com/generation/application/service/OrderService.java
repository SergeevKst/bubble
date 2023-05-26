package com.generation.application.service;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.Order;

import java.util.Set;

public interface OrderService {

    OrderReadDto findByIdWithUser(Integer id);

    Set<OrderReadDto> findByUserId(Integer id);

    Set<OrderReadDto> findOrderByAddress(Address address);

    OrderReadDto findById(Integer id);

    UserReadDto saveOrder(OrderCreateUpdateDto orderCreateUpdateDto, String login);

    OrderReadDto update(OrderCreateUpdateDto orderCreateUpdateDto);

}
