package com.generation.application.service;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.entity.Storehouse;
import com.generation.application.mapper.StorehouseMapper;
import com.generation.application.repository.StorehouseRepository;
import com.generation.application.service.ipml.StorehouseServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorehouseServiceImplTest {

    @Mock
    private StorehouseRepository storehouseRepository;
    @Mock
    private StorehouseMapper storeHouseMapper;
    @InjectMocks
    private StorehouseServiceImpl storehouseService;
    private Storehouse bubbleTest;
    private StorehouseReadDto bubbleDtoTest;
    private StorehouseCreateUpdateDto bubbleCreateDtoTest;
    @BeforeEach
    void setUp() {
        bubbleTest=Storehouse.builder().id(1)
                .balance(new BigDecimal(44))
                .materialName("Pink bubble")
                .materialCount(228).build();

        bubbleDtoTest= new StorehouseReadDto(1,"Pink bubble",21);

        bubbleCreateDtoTest = new StorehouseCreateUpdateDto("Pink bubble", new BigDecimal(228), 8);
    }

    @Test
    void checkFindMaterialCountByName() {
        doReturn(bubbleTest.getMaterialCount())
                .when(storehouseRepository).findMaterialCountByMaterialName(bubbleTest.getMaterialName());

        int actual = storehouseService.findMaterialCountByName(bubbleTest.getMaterialName());

        assertThat(actual).isEqualTo(bubbleTest.getMaterialCount());
    }

    @Test
    void checkFindAllItem() {
        List<Storehouse> storehouseList = new ArrayList<>();
        storehouseList.add(bubbleTest);
        when(storehouseRepository.findAll()).thenReturn(storehouseList);
        when(storeHouseMapper.toDto(bubbleTest)).thenReturn(bubbleDtoTest);
        Set<StorehouseReadDto> actual = storehouseService.findAllItem();
        assertThat(actual).contains(bubbleDtoTest);
    }


    @Test
    void add() {
        when(storeHouseMapper.toEntity(bubbleCreateDtoTest)).thenReturn(bubbleTest);
        when(storehouseRepository.save(bubbleTest)).thenReturn(bubbleTest);
        when(storeHouseMapper.toDto(bubbleTest)).thenReturn(bubbleDtoTest);
        StorehouseReadDto actual = storehouseService.add(bubbleCreateDtoTest);
        assertThat(actual).isEqualTo(bubbleDtoTest);
    }

    @AfterEach
    public void cleanUp() {
        bubbleTest = null;
        bubbleCreateDtoTest=null;
        bubbleDtoTest=null;
    }

}
