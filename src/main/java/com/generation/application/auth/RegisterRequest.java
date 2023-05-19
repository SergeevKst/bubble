package com.generation.application.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record RegisterRequest(String login,
                              String phoneNumber,
                              String firstName,
                              String lastName,
                              String password
                              ) {

}
