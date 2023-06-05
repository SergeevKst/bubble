package com.generation.application.mapper;

import com.generation.application.dto.OrderDetailsCreateUpdateDto;
import com.generation.application.dto.OrderDetailsReadDto;
import com.generation.application.entity.OrderDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    OrderDetailsReadDto toDto(OrderDetails entity);

    OrderDetails toEntity(OrderDetailsCreateUpdateDto dto);
}
