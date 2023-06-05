package com.generation.application.auth;


import java.math.BigDecimal;

public record RegisterRequestDto(String login,
                                 String phoneNumber,
                                 String firstName,
                                 String lastName,
                                 String password,
                                 BigDecimal balance
) {

}
