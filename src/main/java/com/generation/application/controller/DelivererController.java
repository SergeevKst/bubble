package com.generation.application.controller;

import com.generation.application.dto.OrderReadDto;
import com.generation.application.model.OrderStatus;
import com.generation.application.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliverer")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('DELIVERER') or hasAuthority('OWNER')")
public class DelivererController {

    private final OrderService orderService;


    //TODO: add method findByOrdersInProgress and other
    @GetMapping("/ordersPaid")
    public ResponseEntity<List<OrderReadDto>> getOrdersPaid() {
        return ResponseEntity.ok(orderService.findAllOrdersPaid());
    }


    @PostMapping("/ordersPaid/{id}")
    public ResponseEntity takeOrder(@RequestParam Integer id) {
        orderService.changeStatusOrder(id, OrderStatus.IN_PROGRESS);
        return ResponseEntity.ok("Order in progress");
    }

    @PostMapping("/orderDone/{id}")
    public ResponseEntity doneOrder(@PathVariable Integer id) {
        orderService.changeStatusOrder(id, OrderStatus.DONE);
        return ResponseEntity.ok("Order done");
    }

    @PostMapping("/orderDecline/{id}")
    public ResponseEntity orderDecline(@PathVariable Integer id) {
        orderService.changeStatusOrder(id, OrderStatus.PAID);
        return ResponseEntity.ok("Decline order");
    }
}
