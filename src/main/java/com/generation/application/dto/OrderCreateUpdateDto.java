package com.generation.application.dto;

import java.util.List;

public record OrderCreateUpdateDto(List<UserReadDto> users,
                                   OrderDetailsReadDto orderDetails) {

}
