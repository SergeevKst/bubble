package com.generation.application.mapstructMapper;

import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toDto(User entity);

    User toEntity(UserCreateUpdateDto dto);
}
