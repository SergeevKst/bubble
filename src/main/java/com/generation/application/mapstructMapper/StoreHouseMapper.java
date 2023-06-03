package com.generation.application.mapstructMapper;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.Storehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreHouseMapper {

    StorehouseReadDto toDto(Order entity);

    Storehouse toEntity(StorehouseCreateUpdateDto dto);
}
