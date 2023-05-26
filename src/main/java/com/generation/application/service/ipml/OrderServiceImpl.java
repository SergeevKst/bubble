package com.generation.application.service.ipml;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.Mapper;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Mapper<Order, OrderReadDto> orderReadMapper;
    private final Mapper<OrderCreateUpdateDto, Order> orderFromDtoToEntityMapper;
    private final Mapper<User, UserReadDto> userReadDtoMapper;

    @Override
    @Transactional
    public OrderReadDto findByIdWithUser(Integer id) {
        Order order = orderRepository.findByIdWithUser(id);
        return orderReadMapper.map(order);
    }

    @Override
    @Transactional
    public Set<OrderReadDto> findByUserId(Integer id) {
        Set<Order> orderSet = orderRepository.findOrderByUserId(id);
        Set<OrderReadDto> orderReadDtoSet = new HashSet<>();
        for (Order order : orderSet) {
            orderReadDtoSet.add(orderReadMapper.map(order));
        }
        return orderReadDtoSet;
    }

    @Override
    @Transactional
    public Set<OrderReadDto> findOrderByAddress(Address address) {
        Set<Order> orderSet = orderRepository.findOrderByAddress(address.getCity(), address.getStreet(), address.getHouseNumber(),
                address.getApartmentNumber());
        Set<OrderReadDto> orderReadDtoSet = new HashSet<>();
        for (Order order : orderSet) {
            orderReadDtoSet.add(orderReadMapper.map(order));
        }
        return orderReadDtoSet;
    }

    @Override
    @Transactional
    public OrderReadDto findById(Integer id) {
        return orderReadMapper.map(orderRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public UserReadDto saveOrder(OrderCreateUpdateDto orderCreateUpdateDto, String login) {
        Order order = orderFromDtoToEntityMapper.map(orderCreateUpdateDto);
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getOrders() != null) {
            user.getOrders().add(order);
        } else {
            Set<Order> orderSet = new HashSet<>();
            orderSet.add(order);
            user.setOrders(orderSet);
        }
        return userReadDtoMapper.map(userRepository.save(user));
    }

    @Override
    @Transactional
    public OrderReadDto update(OrderCreateUpdateDto orderCreateUpdateDto) {
        return orderReadMapper.map(orderRepository.save(orderFromDtoToEntityMapper.map(orderCreateUpdateDto)));
    }

}