package com.generation.application.service.ipml;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.mapstructMapper.UserMapper;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.UserService;
import com.generation.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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
        User user = userRepository.findByLogin(login).orElse(null);
        Order order = orderRepository.findById(idOrder).orElse(null);
        user.getOrders().remove(order);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public Set<UserReadDto> findAllEmployee() {
        Set<User> userset = userRepository.findAllEployee(Role.USER);
        Set<UserReadDto> userReadDto = new HashSet<>();
        for(User user:userset){
            userReadDto.add(userMapper.toDto(user));
        }
        return userReadDto;
    }
}
