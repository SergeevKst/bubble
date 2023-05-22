package com.generation.application.mapper;

import com.generation.application.dto.OrderDetailsReadDto;
import com.generation.application.entity.OrderDetails;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDetailsReadMapper {

    OrderDetailsReadDto map(OrderDetails entity);
}
