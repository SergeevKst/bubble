package com.generation.application.controller;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.model.Role;
import com.generation.application.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class OrdersController {


    private final OrderService orderService;

    @PreAuthorize("hasAuthority('OWNER') or hasAuthority('MANAGER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderReadDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PostMapping("/create")
    public ResponseEntity<UserReadDto> createOrder(@RequestBody OrderCreateUpdateDto createUpdateDto, @RequestBody String login){
        return ResponseEntity.ok(orderService.saveOrder(createUpdateDto, login));
    }

    //TODO: update by id
    @PostMapping("/update")
    public ResponseEntity<OrderReadDto> update(@RequestBody OrderCreateUpdateDto createUpdateDto){
        return ResponseEntity.ok(orderService.update(createUpdateDto));
    }

    @GetMapping("/ordersUser/{id}")
    public ResponseEntity<Set<OrderReadDto>> getOrdersByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findByUserId(id));
    }

    //TODO: add method deleteOrderById
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity deleteOrder(@PathVariable Integer id){
//        return ResponseEntity.ok(orderService.deleteOrderById);
//    }

}
