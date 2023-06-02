package com.generation.application.mapper;

import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.entity.Storehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorehouseReadMapper {

    StorehouseReadDto map(Storehouse entity);
}
