package com.generation.application.mapper.impl;

import com.generation.application.dto.OrderDetailsCreateUpdateDto;
import com.generation.application.entity.OrderDetails;
import com.generation.application.mapper.Mapper;
import com.generation.application.model.OrderStatus;
import com.generation.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderDetailsCreateUpdateMapper implements Mapper<OrderDetailsCreateUpdateDto, OrderDetails> {

    private final OrderRepository orderRepository;

    @Override
    public OrderDetails map(OrderDetailsCreateUpdateDto object) {
        return OrderDetails.builder()
                .cost(object.cost())
                .bubbleCount(object.bubbleCount())
                .deliveryDate(object.deliveryDate())
                .createDate(LocalDate.now())
                .status(OrderStatus.NEW)
                .address(object.address())
                .order(orderRepository.findById(object.orderId()).get())
                .build();
    }

    @Override
    public OrderDetails map(OrderDetailsCreateUpdateDto fromObject, OrderDetails toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(OrderDetailsCreateUpdateDto fromObject, OrderDetails toObject) {
        toObject.setCost(fromObject.cost());
        toObject.setBubbleCount(fromObject.bubbleCount());
        toObject.setDeliveryDate(fromObject.deliveryDate());
        toObject.setStatus(fromObject.status());
        toObject.setAddress(fromObject.address());
    }
}
