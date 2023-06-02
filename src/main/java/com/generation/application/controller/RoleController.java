package com.generation.application.controller;


import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
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

    //TODO: add method findByAllEmployee
//    @GetMapping("/employee")
//    public ResponseEntity<Set<UserReadDto>> getAllEmployee(){
//        return ResponseEntity.ok(userService.findByAllEmployee);
//    }


    //Becrypted password for User(Employee)
    @PostMapping("/createUser")
    public ResponseEntity<String> createUserWithRole(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok("User created");
    }

    //TODO: Delete user by login
//    @DeleteMapping("/deleteUser")
//    public ResponseEntity deleteUser(@RequestBody String login){
//        return ResponseEntity.ok(userService.delete(login));
//    }
}
