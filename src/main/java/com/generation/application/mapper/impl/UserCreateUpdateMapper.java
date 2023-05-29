package com.generation.application.mapper.impl;

import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.entity.User;
import com.generation.application.mapper.Mapper;
import com.generation.application.model.Role;
import org.springframework.stereotype.Component;

@Component
public class UserCreateUpdateMapper implements Mapper<UserCreateUpdateDto, User> {

    @Override
    public User map(UserCreateUpdateDto object) {
        return User.builder()
                .phoneNumber(object.phoneNumber())
                .login(object.login())
                .role(Role.valueOf(object.role()))
                .firstName(object.firstName())
                .lastName(object.lastName())
                .password(object.password())
                .build();
    }

    @Override
    public User map(UserCreateUpdateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateUpdateDto fromObject, User toObject) {
        toObject.setLogin(fromObject.login());
        toObject.setPassword(fromObject.password());
        toObject.setPhoneNumber(fromObject.phoneNumber());
        toObject.setRole(Role.valueOf(fromObject.role()));
        toObject.setFirstName(fromObject.firstName());
        toObject.setLastName(fromObject.lastName());
    }
}
