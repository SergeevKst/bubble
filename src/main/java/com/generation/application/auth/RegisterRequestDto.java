package com.generation.application.auth;



public record RegisterRequestDto(String login,
                                 String phoneNumber,
                                 String firstName,
                                 String lastName,
                                 String password
                              ) {

}
