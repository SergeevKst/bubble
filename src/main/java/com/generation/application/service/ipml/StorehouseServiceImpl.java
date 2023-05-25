package com.generation.application.service.ipml;

import com.generation.application.repository.StorehouseRepository;
import com.generation.application.service.StorehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StorehouseServiceImpl implements StorehouseService {

    private final StorehouseRepository storehouseRepository;
    @Override
    @Transactional
    public int findMaterialCountById(String name) {
        return storehouseRepository.findMaterialCountByMaterialName(name);
    }
}
