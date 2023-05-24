package com.generation.application.service;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.entity.Address;

import java.util.Set;

public interface OrderService {

    OrderReadDto findByIdWithUser(Integer id);

    Set<OrderReadDto> findByUserId(Integer id);

    Set<OrderReadDto> findOrderByAddress(Address address);

    void saveOrUpdate(OrderCreateUpdateDto orderDto);

}
