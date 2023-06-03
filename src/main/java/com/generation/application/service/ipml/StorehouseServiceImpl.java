package com.generation.application.service.ipml;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.entity.Storehouse;
import com.generation.application.mapper.StorehouseCreateUpdateMapper;
import com.generation.application.mapper.StorehouseReadMapper;
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
    private final StorehouseReadMapper storehouseReadMapper;
    private final StorehouseCreateUpdateMapper storehouseCreateUpdateMapper;

    @Override
    @Transactional
    public int findMaterialCountById(String name) {
        return storehouseRepository.findMaterialCountByMaterialName(name);
    }

    @Transactional
    @Override
    public Set<StorehouseReadDto> findAllItem (){
        List<Storehouse> storehouses = storehouseRepository.findAll();
        Set<StorehouseReadDto> storehouseReadDtos = new HashSet<>();
        for(Storehouse storehouse:storehouses){
            storehouseReadDtos.add(storehouseReadMapper.map(storehouse));
        }
        return storehouseReadDtos;
    }

    @Override
    public StorehouseReadDto add(StorehouseCreateUpdateDto item) {
        return storehouseReadMapper.map(
                storehouseRepository.save(
                        storehouseCreateUpdateMapper.map(item)));
    }
}
