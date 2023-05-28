package com.generation.application.service;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.impl.UserReadMapper;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.ipml.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock private UserRepository userRepository;
    @Mock private UserReadMapper userReadMapper;
    @Mock private OrderRepository orderRepository;
    @Spy
    private HashSet<Order> mockHashSetWithOrder;
    @InjectMocks private UserServiceImpl userService;
    private User testUser;
    private UserReadDto userReadDto;
    private Order testOrder;
    @BeforeEach
    public void setUp() {
        mockHashSetWithOrder=new HashSet<>();
        testUser = User.builder()
                .id(1)
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .orders(Set.of(Order.builder()
                        .id(1)
                        .users(null)
                        .orderDetails(null)
                        .build()))
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();

        userReadDto = new UserReadDto(
                1,
                "test",
                Role.USER.name(),
                "Test",
                "Test",
                "Test",
                null);

        testOrder = Order.builder()
                .id(1)
                .users(Set.of(testUser))
                .orderDetails(null)
                .build();
    }

    @Test
    public void findUserByLoginTest() {
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser)
        );
        var userByLogin = userService.findByLogin(testUser.getLogin());
        //then
        Assertions.assertThat(userByLogin).isNotNull();
    }

    @Test
    public void findUserByLoginReturnUserReadDtoTest() {
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser)
                );
        when(userReadMapper.map(testUser))
                .thenReturn(userReadDto);
        var userByLogin = userService.findUserByLogin(testUser.getLogin());
        //then
        Assertions.assertThat(userByLogin).isNotNull();
    }
    @Test
    public void findByIdWithOrderTest(){
        //when
        when(userRepository.findByIdWithOrder(1))
                .thenReturn(testUser);
        when(userReadMapper.map(testUser))
                .thenReturn(userReadDto);
        UserReadDto byIdWithOrder = userService.findByIdWithOrder(1);
        Assertions.assertThat(byIdWithOrder).isNotNull();
    }

    @Test
    public void saveUserTest() {
        //when
        userService.save(testUser);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        var userValue = userArgumentCaptor.getValue();
        //then
        assertThat(userValue).isEqualTo(testUser);
    }

    @Test
    public void whenSaveUserReturnUserReadDto() {
        //when
        when(userRepository.save(testUser))
                .thenReturn(testUser);
        when(userReadMapper.map(testUser))
                .thenReturn(userReadDto);
        var savedUserLikeDto = userService.save(testUser);
        //then
        assertThat(savedUserLikeDto).isNotNull();
    }
    @Test
    @Disabled
    public void setOrderToEmployeeTest(){
        User mock = mock(testUser.getClass());
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));

        when(orderRepository.findById(testOrder.getId()))
                .thenReturn(Optional.ofNullable(testOrder));

        when(mock.getOrders())
                .thenReturn(mockHashSetWithOrder);

        when(mockHashSetWithOrder.add(testOrder))
                .thenReturn(true);

        when(userReadMapper.map(testUser))
                .thenReturn(userReadDto);

        when(userRepository.save(testUser))
                .thenReturn(testUser);

        UserReadDto userReadDto1 = userService.setOrderToEmployee(
                testUser.getLogin(),
                testOrder.getId()
        );
        assertThat(userReadDto1).isNotNull();
    }
    @Test
    @Disabled
    public void removeOrderFromEmployeeTest(){
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));

        when(orderRepository.findById(testOrder.getId()))
                .thenReturn(Optional.ofNullable(testOrder));
        when(userReadMapper.map(testUser))
                .thenReturn(userReadDto);

        when(userRepository.save(testUser))
                .thenReturn(testUser);

        UserReadDto userReadDto1 = userService.removeOrderFromEmployee(
                testUser.getLogin(),
                testOrder.getId()
        );

        assertThat(userReadDto1).isNotNull();
    }
    @AfterEach
    public void cleanUp() {
        userReadDto=null;
        testOrder=null;
        testUser = null;
    }
}
