package com.generation.application.controller;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class OrdersController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderReadDto>> getOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody OrderCreateUpdateDto createUpdateDto,@RequestBody String login){
        return ResponseEntity.ok(orderService.saveOrder(createUpdateDto, login));
    }

    //TODO: update by id
    @PostMapping("/order")
    public ResponseEntity update(@RequestBody OrderCreateUpdateDto createUpdateDto){
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
