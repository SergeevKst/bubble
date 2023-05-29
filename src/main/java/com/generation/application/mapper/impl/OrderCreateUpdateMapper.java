package com.generation.application.mapper.impl;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.entity.Order;
import com.generation.application.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreateUpdateMapper implements Mapper<OrderCreateUpdateDto, Order> {

    private final OrderDetailsCreateUpdateMapper orderDetailsCreateUpdateMapper;

    @Override
    public Order map(OrderCreateUpdateDto object) {
        return Order.builder()
                .id(object.id())
                .orderDetails(
                        orderDetailsCreateUpdateMapper.map(object.orderDetails())
                )
                .build();
    }
}
