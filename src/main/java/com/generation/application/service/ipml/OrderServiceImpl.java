package com.generation.application.service.ipml;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.OrderMapper;
import com.generation.application.mapper.UserMapper;
import com.generation.application.model.OrderStatus;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public OrderReadDto findByIdWithUser(Integer id) {
        Order order = orderRepository.findByIdWithUser(id);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public Set<OrderReadDto> findByUserId(Integer id) {
        Set<Order> orderSet = orderRepository.findOrderByUserId(id);
        Set<OrderReadDto> orderReadDtoSet = new HashSet<>();
        for (Order order : orderSet) {
            orderReadDtoSet.add(orderMapper.toDto(order));
        }
        return orderReadDtoSet;
    }

    @Override
    @Transactional
    public List<OrderReadDto> findAllOrders() {
        List<Order> orderSet = orderRepository.findAll();
        List<OrderReadDto> orderReadDtoSet = new LinkedList<>();
        for (Order order : orderSet) {
            orderReadDtoSet.add(orderMapper.toDto(order));
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
            orderReadDtoSet.add(orderMapper.toDto(order));
        }
        return orderReadDtoSet;
    }

    @Override
    @Transactional
    public OrderReadDto findById(Integer id) {
        return orderMapper.toDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public UserReadDto saveOrder(OrderCreateUpdateDto orderCreateUpdateDto, String login) {
        Order order = orderMapper.toEntity(orderCreateUpdateDto);
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getOrders() != null) {
            user.getOrders().add(order);
        } else {
            Set<Order> orderSet = new HashSet<>();
            orderSet.add(order);
            user.setOrders(orderSet);
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public OrderReadDto update(OrderCreateUpdateDto orderCreateUpdateDto) {
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderCreateUpdateDto)));
    }

    @Transactional
    @Override
    public void changeStatusOrder(Integer id, OrderStatus orderStatus) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.getOrderDetails().setStatus(orderStatus);
        orderRepository.save(order);

    }

    @Transactional
    @Override
    public List<OrderReadDto> findAllOrdersPaid() {
        var ordersPaidList = orderRepository.findByPaidStatus();
        return ordersPaidList.stream()
                .map(orderMapper::toDto)
                .toList();
    }
}