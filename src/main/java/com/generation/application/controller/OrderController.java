package com.generation.application.controller;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.model.OrderStatus;
import com.generation.application.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAuthority('OWNER') or hasAuthority('MANAGER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderReadDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PostMapping("/create")
    public ResponseEntity<UserReadDto> createOrder(@RequestBody OrderCreateUpdateDto createUpdateDto,
                                                   @RequestParam String login) {
        return ResponseEntity.ok(orderService.saveOrder(createUpdateDto, login));
    }

    @PostMapping("/update")
    public ResponseEntity<OrderReadDto> update(@RequestBody OrderCreateUpdateDto createUpdateDto) {
        return ResponseEntity.ok(orderService.update(createUpdateDto));
    }

    @GetMapping("/ordersUser/{id}")
    public ResponseEntity<Set<OrderReadDto>> getOrdersByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findByUserId(id));
    }

    @PreAuthorize("hasAuthority('OWNER') or hasAuthority('MANAGER')")
    @PostMapping("/changedStatus")
    public ResponseEntity<String> changedStatus(@RequestParam Integer id,
                                                @RequestParam OrderStatus orderStatus) {
        orderService.changeStatusOrder(id, orderStatus);
        return ResponseEntity.ok("Status changed");
    }
}
