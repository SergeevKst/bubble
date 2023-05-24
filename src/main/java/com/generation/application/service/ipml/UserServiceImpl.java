package com.generation.application.service.ipml;

import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.mapper.Mapper;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.UserService;
import com.generation.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper<User,UserReadDto> userReadMapper;
    private final Mapper<UserCreateUpdateDto,User> userCreateUpdateDtoUserMapper;
    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(()->new UsernameNotFoundException("Login not found"));
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserReadDto findByIdWithOrder(Integer id) {
        User user = userRepository.findByIdWithOrder(id);
        return userReadMapper.map(user);
    }
}
