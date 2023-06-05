package com.generation.application.service.ipml;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.mapper.UserMapper;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.UserService;
import com.generation.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found"));
    }

    @Override
    public UserReadDto findUserByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found")));
    }

    @Override
    @Transactional
    public UserReadDto save(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserReadDto findByIdWithOrder(Integer id) {
        User user = userRepository.findByIdWithOrder(id);
        return userMapper.toDto(user);
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
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserReadDto removeOrderFromEmployee(String login, Integer idOrder) {
        User user = userRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        Order order = orderRepository.findById(idOrder).orElseThrow(
                () -> new IllegalArgumentException("Order not found")
        );
        user.getOrders().remove(order);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public List<UserReadDto> findAllEmployee() {
        List<User> users = userRepository.findAllEmployee(Role.USER);
        List<UserReadDto> userReadDto = new ArrayList<>();
        for (User user : users) {
            userReadDto.add(userMapper.toDto(user));
        }
        return userReadDto;
    }

    @Override
    public User findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public UserReadDto findById(Integer userId) {
        return userMapper.toDto(
                userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("Id not found"))
        );
    }

    @Override
    public void updateUserBalance(Integer userId, BigDecimal balance) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Id not found"));
        user.setBalance(balance);
        userRepository.save(user);
    }
}
