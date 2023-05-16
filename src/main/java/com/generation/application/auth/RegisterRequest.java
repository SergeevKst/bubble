package com.generation.application.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private String login;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String password;
}
