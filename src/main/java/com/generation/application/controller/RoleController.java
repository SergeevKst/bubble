package com.generation.application.controller;


import com.generation.application.entity.User;
import com.generation.application.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class RoleController {
    //TODO: create and delete user with role(Manager,Deliverer) for OWNER

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity createUserWithRole(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok("User created");
    }
}
