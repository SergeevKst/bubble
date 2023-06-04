package com.generation.application.service;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;

import java.util.Set;

public interface StorehouseService {

    int findMaterialCountById(String name);

    Set<StorehouseReadDto> findAllItem();

    StorehouseReadDto add(StorehouseCreateUpdateDto item);

}
