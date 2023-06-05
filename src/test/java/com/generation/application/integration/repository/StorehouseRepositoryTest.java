package com.generation.application.integration.repository;

import com.generation.application.entity.Storehouse;
import com.generation.application.repository.StorehouseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StorehouseRepositoryTest {

    @Autowired
    private StorehouseRepository storehouseRepository;
    private Storehouse storehouse;

    @BeforeEach
    public void setUp() {
        storehouse = Storehouse.builder()
                .id(1)
                .balance(new BigDecimal(1000))
                .materialCount(100)
                .materialName("Test")
                .build();
    }

    @Test
    public void saveStorehouseTest() {
        //when
        var savedStorehouse = storehouseRepository.save(storehouse);
        //then
        Assertions.assertThat(savedStorehouse).isNotNull();
    }

    @Test
    public void findMaterialCountByMaterialNameTest() {
        //when
        storehouseRepository.save(storehouse);
        int materialCountByMaterialName = storehouseRepository
                .findMaterialCountByMaterialName(storehouse.getMaterialName());
        //then
        Assertions.assertThat(materialCountByMaterialName).isEqualTo(100);
    }

    @AfterEach
    public void cleanUp() {
        storehouse = null;
    }
}
