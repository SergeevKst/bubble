package com.generation.application.dto;

import com.generation.application.entity.Address;
import com.generation.application.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderDetailsReadDto(int id,
                                  BigDecimal cost,
                                  int bubbleCount,
                                  LocalDate createDate,
                                  LocalDate deliveryDate,
                                  OrderStatus status,
                                  Address address,
                                  int orderId) {

}
