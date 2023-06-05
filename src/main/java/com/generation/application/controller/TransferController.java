package com.generation.application.controller;

import com.generation.application.service.MoneyTransferService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfer")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class TransferController {
    private final MoneyTransferService moneyTransferService;
    @PostMapping(value = "/pay")
    public ResponseEntity<String> pay(@RequestParam Integer userId,
                                      @RequestParam Integer orderId){
        moneyTransferService.transfer(userId, orderId);
        return ResponseEntity.ok("Transaction success");
    }
}
