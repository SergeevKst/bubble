package com.generation.application.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/v1/hello")
@Tag(name = "Basic", description = "basic method")
public class HelloController {
    @GetMapping("/say")
    @Operation(summary = "new generation")
    public String sayHello() {
        return "Hello from New Generation";
    }
}
