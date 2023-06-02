package com.generation.application.controller;


import com.generation.application.auth.AuthenticationService;
import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('OWNER')")
public class RoleController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    //TODO: add method findByAllEmployee
    @GetMapping("/employee")
    public ResponseEntity<Set<UserReadDto>> getAllEmployee(){
        return ResponseEntity.ok(userService.findAllEmployee());
    }


    @PostMapping("/createUser")
    public ResponseEntity<UserReadDto> createUserWithRole(@RequestBody UserCreateUpdateDto user){
        return ResponseEntity.ok(authenticationService.registrationEmployee(user));
    }

    //TODO: Delete user by login
//    @DeleteMapping("/deleteUser")
//    public ResponseEntity deleteUser(@RequestBody String login){
//        return ResponseEntity.ok(userService.delete(login));
//    }
}
