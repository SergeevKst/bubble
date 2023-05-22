package com.generation.application.mapper;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.entity.Storehouse;
import org.mapstruct.Mapper;

@Mapper
public interface StorehouseCreateUpdateMapper {

    Storehouse map(StorehouseCreateUpdateDto dto);
}
