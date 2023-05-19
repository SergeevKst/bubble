package com.generation.application.controller;

import com.generation.application.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    private final AuthenticationService authenticationService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/roles")
    public ResponseEntity<String> getRoles(@NonNull HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.checkRole(request));
    }
}

