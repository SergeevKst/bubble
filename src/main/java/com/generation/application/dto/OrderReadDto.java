package com.generation.application.dto;

import com.generation.application.model.Role;

import java.util.Map;

public record OrderReadDto(int id,
                           Map<String, Role> userLogins,
                           OrderDetailsReadDto orderDetails) {

}
