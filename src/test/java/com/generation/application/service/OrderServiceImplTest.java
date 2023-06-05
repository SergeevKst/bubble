package com.generation.application.service;

import com.generation.application.dto.OrderCreateUpdateDto;
import com.generation.application.dto.OrderDetailsCreateUpdateDto;
import com.generation.application.dto.OrderDetailsReadDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.Order;
import com.generation.application.entity.OrderDetails;
import com.generation.application.entity.User;
import com.generation.application.mapper.OrderMapper;
import com.generation.application.mapper.UserMapper;
import com.generation.application.model.OrderStatus;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.ipml.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private UserMapper userMapper;
    private User testUser;
    private OrderReadDto orderReadDto;
    private Order testOrder;
    private Order anotherTestOrder;
    private Address testAddress;
    private OrderDetails orderDetails;
    private OrderCreateUpdateDto orderCreateUpdateDto;
    private UserReadDto userReadDto;

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
                .id(1)
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .orders(
                        Set.of(Order.builder()
                                .id(1)
                                .users(new HashSet<>())
                                .orderDetails(orderDetails)
                                .build())
                )
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();


        orderReadDto = new OrderReadDto(
                1,
                new HashMap<>(),
                new OrderDetailsReadDto(
                        1,
                        new BigDecimal(100),
                        100,
                        LocalDate.now(),
                        LocalDate.now().plusDays(1),
                        OrderStatus.NEW,
                        Address.builder().build()
                ));

        testOrder = Order.builder()
                .id(1)
                .users(new HashSet<>())
                .orderDetails(orderDetails)
                .build();

        anotherTestOrder = Order.builder()
                .id(2)
                .users(new HashSet<>())
                .orderDetails(orderDetails)
                .build();

        testAddress = Address.builder()
                .apartmentNumber(1)
                .city("Test")
                .houseNumber(1)
                .street("Test")
                .build();
        orderDetails = new OrderDetails(
                1,
                new BigDecimal(10000),
                100,
                LocalDate.now(),
                LocalDate.now(),
                OrderStatus.NEW,
                testAddress,
                testOrder);

        var orderDetailsDto = new OrderDetailsReadDto(
                1,
                new BigDecimal(10000),
                100,
                LocalDate.now(),
                LocalDate.now(),
                OrderStatus.NEW,
                testAddress);

        orderReadDto = new OrderReadDto(
                1,
                new HashMap<>(),
                orderDetailsDto);

        orderCreateUpdateDto = new OrderCreateUpdateDto(1,
                new OrderDetailsCreateUpdateDto(
                        orderDetails.getId(),
                        orderDetails.getCost(),
                        orderDetails.getBubbleCount(),
                        orderDetails.getCreateDate(),
                        orderDetails.getDeliveryDate(),
                        orderDetails.getStatus(),
                        testAddress
                )
        );
        userReadDto = new UserReadDto(
                1,
                "Test",
                "test",
                Role.USER.name(),
                "Test",
                "Test",
                new HashSet<>(),
                new BigDecimal(1000));

        testOrder.setOrderDetails(orderDetails);
        testOrder.setUsers(new HashSet<>());
        testUser.setOrders(new HashSet<>());
    }

    @Test
    public void findByIdWithUserTest() {
        //when
        when(orderRepository.findByIdWithUser(testOrder.getId()))
                .thenReturn(testOrder);
        when(orderMapper.toDto(testOrder))
                .thenReturn(orderReadDto);
        var orderReadDtoAfterMethodInvoke = orderService.findByIdWithUser(testOrder.getId());
        //then
        assertThat(orderReadDtoAfterMethodInvoke).isEqualTo(orderReadDto);
    }

    @Test
    public void findByUserIdTest() {
        when(orderRepository.findOrderByUserId(testUser.getId()))
                .thenReturn(Set.of(testOrder));
        Set<OrderReadDto> byUserId = orderService.findByUserId(testUser.getId());
        assertThat(byUserId).isNotEmpty();
    }

    @Test
    public void findByIdTest() {
        doReturn(Optional.ofNullable(testOrder)).when(orderRepository).findById(any());
        doReturn(orderReadDto).when(orderMapper).toDto(any());
        var orderAfterMethodInvoke = orderService.findById(1);
        assertThat(orderAfterMethodInvoke).isEqualTo(orderReadDto);
    }

    @Test
    void saveOrderTest() {
        var mock = Mockito.mock(testUser.getClass());

        when(orderMapper.toEntity(orderCreateUpdateDto))
                .thenReturn((testOrder));
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));
        when(userRepository.save(testUser))
                .thenReturn(testUser);
        when(userMapper.toDto(testUser))
                .thenReturn(userReadDto);

        UserReadDto save = orderService.saveOrder(orderCreateUpdateDto, userReadDto.login());
        assertThat(save).isEqualTo(userReadDto);
    }

    @Test
    void updateTest() {
        when(orderMapper.toDto(testOrder))
                .thenReturn(orderReadDto);
        when(orderRepository.save(testOrder))
                .thenReturn(testOrder);
        when(orderMapper.toEntity(orderCreateUpdateDto))
                .thenReturn((testOrder));
        OrderReadDto update = orderService.update(orderCreateUpdateDto);
        assertThat(update).isEqualTo(orderReadDto);
    }

    @Test
    void checkFindAllTest() {
        List<Order> orderList = new LinkedList<>();
        orderList.add(testOrder);
        doReturn(orderList)
                .when(orderRepository).findAll();
        doReturn(orderReadDto)
                .when(orderMapper).toDto(testOrder);
        List<OrderReadDto> orders = orderService.findAllOrders();

        assertThat(orders).contains(orderReadDto);
        verify(orderMapper, times(1))
                .toDto(any());
    }

    @Test
    void checkChangeStatusOrderTest() {
        Integer id = testOrder.getId();
        doReturn(Optional.of(testOrder))
                .when(orderRepository).findById(id);

        orderService.changeStatusOrder(id, OrderStatus.DONE);

        assertThat(testOrder.getOrderDetails().getStatus()).isEqualTo(OrderStatus.DONE);
    }

    @Test
    void checkFindAllOrdersPaid() {
        ArrayList<Order> expected = new ArrayList<>();
        testOrder.getOrderDetails().setStatus(OrderStatus.PAID);
        expected.add(testOrder);
        expected.add(anotherTestOrder);
        doReturn(expected)
                .when(orderRepository).findByPaidStatus();
        doReturn(orderReadDto)
                .when(orderMapper).toDto(any());

        List<OrderReadDto> actual = orderService.findAllOrdersPaid();

        assertThat(actual).contains(orderReadDto);
        verify(orderMapper, times(2))
                .toDto(any());
    }


    @AfterEach
    public void cleanUp() {
        userReadDto = null;
        orderCreateUpdateDto = null;
        testAddress = null;
        orderDetails = null;
        orderReadDto = null;
        testOrder = null;
        testUser = null;

    }
}
