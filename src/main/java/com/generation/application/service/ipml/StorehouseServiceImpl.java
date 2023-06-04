package com.generation.application.service.ipml;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.entity.Storehouse;
import com.generation.application.mapper.StoreHouseMapper;
import com.generation.application.repository.StorehouseRepository;
import com.generation.application.service.StorehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StorehouseServiceImpl implements StorehouseService {

    private final StorehouseRepository storehouseRepository;
    private final StoreHouseMapper storeHouseMapper;

    @Override
    @Transactional
    public int findMaterialCountByName(String name) {
        return storehouseRepository.findMaterialCountByMaterialName(name);
    }

    @Transactional
    @Override
    public Set<StorehouseReadDto> findAllItem() {
        List<Storehouse> storehouses = storehouseRepository.findAll();
        Set<StorehouseReadDto> storehouseReadDto = new HashSet<>();
        for (Storehouse storehouse : storehouses) {
            storehouseReadDto.add(storeHouseMapper.toDto(storehouse));
        }
        return storehouseReadDto;
    }

    @Override
    public StorehouseReadDto add(StorehouseCreateUpdateDto item) {
        return storeHouseMapper.toDto(
                storehouseRepository.save(
                        storeHouseMapper.toEntity(item)));
    }
}
