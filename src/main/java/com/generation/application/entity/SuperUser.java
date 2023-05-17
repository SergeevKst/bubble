package com.generation.application.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class SuperUser<T extends Serializable> implements BaseEntity<T> {

    private String phoneNumber;
    private String login;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "phoneNumber='" + phoneNumber + '\'' +
               ", login='" + login + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName;
    }
}
