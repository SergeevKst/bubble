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
import com.generation.application.mapper.Mapper;
import com.generation.application.model.OrderStatus;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.ipml.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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
    private Mapper<Order, OrderReadDto> orderReadMapper;
    @Mock
    private Mapper<OrderCreateUpdateDto, Order> orderFromDtoToEntityMapper;
    @Mock
    private Mapper<User, UserReadDto> userReadDtoMapper;
    private User testUser;
    private OrderReadDto orderReadDto;
    private Order testOrder;
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


        testOrder = Order.builder()
                .id(1)
                .users(Set.of(testUser))
                .orderDetails(orderDetails)
                .build();

        testAddress=Address.builder()
                .apartmentNumber(1)
                .city("Test")
                .houseNumber(1)
                .street("Test")
                .build();
         orderDetails=new OrderDetails(
                1,
                 new BigDecimal(10000),
                100,
                 LocalDate.now(),
                 LocalDate.now(),
                 OrderStatus.NEW,
                 testAddress,
                 testOrder);

        var orderDetailsDto=new OrderDetailsReadDto(
                1,
                 new BigDecimal(10000),
                100,
                 LocalDate.now(),
                 LocalDate.now(),
                 OrderStatus.NEW,
                 testAddress,
                 testOrder.getId());

        orderReadDto = new OrderReadDto(
                1,
                Map.of(testUser.getLogin(),testUser.getRole()),
                orderDetailsDto);

        orderCreateUpdateDto=new OrderCreateUpdateDto(1,
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
                Set.of(testOrder.getId()));

        testOrder.setOrderDetails(orderDetails);
        testOrder.setUsers(Set.of(testUser));
        testUser.setOrders(Set.of(testOrder));
    }
    @Test
    public void findByIdWithUserTest(){
        //when
        when(orderRepository.findByIdWithUser(testOrder.getId()))
                .thenReturn(testOrder);
        when(orderReadMapper.map(testOrder))
                .thenReturn(orderReadDto);
        var orderReadDtoAfterMethodInvoke = orderService.findByIdWithUser(testOrder.getId());
        //then
        Assertions.assertThat(orderReadDtoAfterMethodInvoke).isEqualTo(orderReadDto);
    }
    @Test
    public void findByUserIdTest() {
        when(orderRepository.findOrderByUserId(testUser.getId()))
                .thenReturn(Set.of(testOrder));
        Set<OrderReadDto> byUserId = orderService.findByUserId(testUser.getId());
        Assertions.assertThat(byUserId).isNotEmpty();
    }
    @Test
    public void findOrderByAddressTest(){
        when(orderRepository.findOrderByAddress(
                 testAddress.getCity()
                ,testAddress.getStreet()
                ,testAddress.getHouseNumber()
                ,testAddress.getApartmentNumber()))
                .thenReturn(Set.of(testOrder));
        Set<OrderReadDto> orderByAddress = orderService.findOrderByAddress(testAddress);
        Assertions.assertThat(orderByAddress).isNotEmpty();
    }
    @Test
    public void findByIdTest(){
        doReturn(Optional.ofNullable(testOrder)).when(orderRepository).findById(any());
//        doReturn(orderReadDto).when(orderReadMapper).map(any());
        doReturn(orderReadDto).when(orderFromDtoToEntityMapper).map(any());

//        when(orderRepository.findById(1))
//                .thenReturn(Optional.ofNullable(testOrder));
//        when(orderReadMapper.map(testOrder)).thenReturn(orderReadDto);
        var orderAfterMethodInvoke = orderService.findById(1);
        Assertions.assertThat(orderAfterMethodInvoke).isEqualTo(orderReadDto);
    }

    @Test
    @Disabled
    public void saveOrderTest(){
        var mock=Mockito.mock(testUser.getClass());

        when(orderFromDtoToEntityMapper.map(orderCreateUpdateDto))
                .thenReturn((testOrder));
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));
        when(userRepository.save(testUser))
                .thenReturn(testUser);
        when(mock.getOrders())
                .thenReturn(testUser.getOrders());
        when(userReadDtoMapper.map(testUser))
                .thenReturn(userReadDto);

        UserReadDto save = orderService.saveOrder(orderCreateUpdateDto, userReadDto.login());
        Assertions.assertThat(save).isEqualTo(userReadDto);
    }
    @Test
    @Disabled
    public void updateTest(){
        when(orderReadMapper.map(testOrder))
                .thenReturn(orderReadDto);
        when(orderRepository.save(testOrder))
                .thenReturn(testOrder);
        when(orderFromDtoToEntityMapper.map(orderCreateUpdateDto))
                .thenReturn((testOrder));
        OrderReadDto update = orderService.update(orderCreateUpdateDto);
        Assertions.assertThat(update).isEqualTo(orderReadDto);
    }
    @AfterEach
    public void cleanUp() {
        userReadDto=null;
        orderCreateUpdateDto=null;
        testAddress=null;
        orderDetails=null;
        orderReadDto=null;
        testOrder=null;
        testUser = null;
    }
}
