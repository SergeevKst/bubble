package com.generation.application.service;

import com.generation.application.mapper.StoreHouseMapper;
import com.generation.application.repository.StorehouseRepository;
import com.generation.application.service.ipml.StorehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StorehouseServiceImplTest {

    @Mock
    private StorehouseRepository storehouseRepository;
    @Mock
    private StoreHouseMapper storeHouseMapper;
    @InjectMocks
    private StorehouseServiceImpl storehouseService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void checkFindMaterialCountByName() {
        String gold = "gold";
        Integer expected = 1;
        doReturn(expected)
                .when(storehouseRepository).findMaterialCountByMaterialName(gold);

        int actual = storehouseService.findMaterialCountByName(gold);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindAllItem() {

    }

    @Test
    void add() {

    }
}
