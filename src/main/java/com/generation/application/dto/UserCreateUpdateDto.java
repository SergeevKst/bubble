package com.generation.application.dto;

import java.math.BigDecimal;

public record UserCreateUpdateDto(String login,
                                  String password,
                                  String phoneNumber,
                                  String role,
                                  String firstName,
                                  String lastName,
                                  BigDecimal balance) {
}
