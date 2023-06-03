package com.generation.application.service.ipml;

import com.generation.application.auth.AuthenticationService;
import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.mapper.Mapper;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.UserService;
import com.generation.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper<User, UserReadDto> userReadMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found"));
    }

    @Override
    public UserReadDto findUserByLogin(String login) {
        return userReadMapper.map(userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found")));
    }

    @Override
    @Transactional
    public UserReadDto save(User user) {
        return userReadMapper.map(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserReadDto findByIdWithOrder(Integer id) {
        User user = userRepository.findByIdWithOrder(id);
        return userReadMapper.map(user);
    }

    @Override
    @Transactional
    public UserReadDto setOrderToEmployee(String login, Integer idOrder) {
        User user = userRepository.findByLogin(login).orElse(null);
        Order order = orderRepository.findById(idOrder).orElse(null);
        if (user != null && order != null) {
            user.getOrders().add(order);

        } else {
            throw new UsernameNotFoundException("Uncorrected login or id order");
        }
        return userReadMapper.map(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserReadDto removeOrderFromEmployee(String login, Integer idOrder) {
        User user = userRepository.findByLogin(login).orElse(null);
        Order order = orderRepository.findById(idOrder).orElse(null);
        user.getOrders().remove(order);
        return userReadMapper.map(userRepository.save(user));
    }

    @Override
    @Transactional
    public Set<UserReadDto> findAllEmployee() {
        Set<User> userset = userRepository.findAllEployee(Role.USER);
        Set<UserReadDto> userReadDtos = new HashSet<>();
        for(User user:userset){
            userReadDtos.add(userReadMapper.map(user));
        }
        return userReadDtos;
    }
}
