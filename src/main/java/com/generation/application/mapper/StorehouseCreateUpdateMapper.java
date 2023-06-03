package com.generation.application.mapper;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.entity.Storehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorehouseCreateUpdateMapper {

    Storehouse map(StorehouseCreateUpdateDto dto);
}
