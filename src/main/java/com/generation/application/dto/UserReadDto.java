package com.generation.application.dto;

public record UserReadDto(int id,
                          String login,
                          String phoneNumber,
                          String role,
                          String firstName,
                          String lastName) {

}
