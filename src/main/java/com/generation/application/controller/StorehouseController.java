package com.generation.application.controller;

import com.generation.application.dto.StorehouseCreateUpdateDto;
import com.generation.application.dto.StorehouseReadDto;
import com.generation.application.service.StorehouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/storehouse")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class StorehouseController {

    private final StorehouseService storehouseService;


    @GetMapping("/storehouse")
    public ResponseEntity<Set<StorehouseReadDto>> getAllRemainderByStorehouse(){
        return ResponseEntity.ok(storehouseService.findAllItem());
    }

    @GetMapping("/materialCountByName/{name}")
    public ResponseEntity<Integer> getMaterialCountByName(@PathVariable String name){
        return ResponseEntity.ok(storehouseService.findMaterialCountById(name));
    }

    @PostMapping("/addItem")
    @PreAuthorize("hasAuthority('OWNER') or hasAuthority('MANAGER')")
    public ResponseEntity<StorehouseReadDto> addItem(@RequestBody StorehouseCreateUpdateDto item){
        return ResponseEntity.ok(storehouseService.add(item));
    }
}
