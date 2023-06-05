package com.generation.application.mapper;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = OrderDetailsMapper.class)
public interface OrderMapper {

    OrderReadDto toDto(Order entity);

    Order toEntity(OrderCreateUpdateDto dto);
}
