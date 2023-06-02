package com.generation.application.controller;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/deliverer")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('DELIVERER') or hasAuthority('OWNER')")
public class DelivererController {

    private final OrderService orderService;


    //TODO: add method findByOrdersInProgress and other
    @GetMapping("/ordersInProgress")
    public ResponseEntity<Set<OrderCreateUpdateDto>> getOrdersInProgress(){
        return ResponseEntity.ok(orderService.findByOrdersInProgress);
    }



    //changeStatus(idOrder, OrderStatus - статус на который меняем текущий статус)
    @PostMapping("/ordersInProgress/{id}")
    public ResponseEntity takeOrder(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.changeStatusOrderAsignetDeliverer(id));
    }

    @PostMapping("/orderDone/{id}")
    public ResponseEntity doneOrder(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.changeStatusOrderDone(id));
    }

    @PostMapping("/orderDecline/{id}")
    public ResponseEntity orderDecline(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.changeStatusOrderInProgress(id));
    }
}
