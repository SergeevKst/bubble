package com.generation.application.dto;

import java.math.BigDecimal;
import java.util.Set;

public record UserReadDto(int id,
                          String login,
                          String phoneNumber,
                          String role,
                          String firstName,
                          String lastName,
                          Set<Integer> ordersId,
                          BigDecimal balance) {

}
