package com.generation.application.controller;


import com.generation.application.auth.AuthenticationService;
import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('OWNER')")
public class EmployeeController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping()
    public ResponseEntity<List<UserReadDto>> getAllEmployee() {
        return ResponseEntity.ok(userService.findAllEmployee());
    }


    @PostMapping("/createEmployee")
    public ResponseEntity<UserReadDto> createUserWithRole(@RequestBody UserCreateUpdateDto user) {
        return ResponseEntity.ok(authenticationService.registrationEmployee(user));
    }
}
