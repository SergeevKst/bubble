package com.generation.application.repository;

import com.generation.application.entity.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StorehouseRepository extends JpaRepository<Storehouse,Integer> {

    @Query("select s.materialCount from Storehouse s where s.materialName=?1")
    int findMaterialCountByMaterialName(String name);
}
