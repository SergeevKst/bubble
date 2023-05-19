package com.generation.application.controller;

import com.generation.application.auth.AuthenticationRequestDto;
import com.generation.application.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.generation.application.auth.AuthenticationResponseDto;
import com.generation.application.auth.RegisterRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponseDto> authentication(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/healthCheck")
    public ResponseEntity<String> getHealth() {
        return ResponseEntity.ok("Hello from Aston");
    }
}
