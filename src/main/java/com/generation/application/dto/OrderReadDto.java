package com.generation.application.dto;

import java.util.List;

public record OrderReadDto(int id,
                           List<UserReadDto> users,
                           OrderDetailsReadDto orderDetails) {

}
