package com.generation.application.service;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.Order;

import java.util.Set;

public interface OrderService {

    OrderReadDto findByIdWithUser(Integer id);

    Set<OrderReadDto> findByUserId(Integer id);

    Set<OrderReadDto> findOrderByAddress(Address address);

    Order findById(Integer id);

    void saveOrder(OrderCreateUpdateDto orderCreateUpdateDto, String login);

    void update(OrderCreateUpdateDto orderCreateUpdateDto);

}
