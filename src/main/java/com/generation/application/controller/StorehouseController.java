package com.generation.application.controller;

import com.generation.application.service.StorehouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class StorehouseController {

    @Autowired
    StorehouseService storehouseService;


    //TODO: add method findAllMaterials
//    @GetMapping("/storehouse")
//    public ResponseEntity getAllRemainderByStorehouse(){
//        return ResponseEntity.ok(storehouseService.findAllMaterials);
//    }

    @GetMapping("/storehouse/{name}")
    public ResponseEntity getMaterialCountByName(@PathVariable String name){
        return ResponseEntity.ok(storehouseService.findMaterialCountById(name));
    }
}
