package com.generation.application.dto;

public record UserCreateUpdateDto(String login,
                                  String password,
                                  String phoneNumber,
                                  String role,
                                  String firstName,
                                  String lastName) {

}
