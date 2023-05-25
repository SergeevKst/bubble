package com.generation.application.service.ipml;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.mapper.Mapper;
import com.generation.application.repository.OrderRepository;
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
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(()->new UsernameNotFoundException("Login not found"));
    }

    @Override
    public UserReadDto findUserByLogin(String login) {
        return userReadMapper.map(userRepository.findByLogin(login)
                .orElseThrow(()->new UsernameNotFoundException("Login not found")));
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

    @Override
    @Transactional
    public void setOrderToEmployee(String login, Integer idOrder) {
        User user = userRepository.findByLogin(login).orElse(null);
        Order order = orderRepository.findById(idOrder).orElse(null);
        if(user!=null&&order!=null){
            user.getOrders().add(order);
            userRepository.save(user);
        }

    }

    @Override
    public void removeOrderFromEmployee(String login, Integer idOrder) {
        User user = userRepository.findByLogin(login).orElse(null);
        Order order = orderRepository.findById(idOrder).orElse(null);
        user.getOrders().remove(order);
        userRepository.save(user);
    }
}
