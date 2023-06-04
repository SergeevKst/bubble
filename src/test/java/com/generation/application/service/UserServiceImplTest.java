package com.generation.application.service;

import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.mapper.UserMapper;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import com.generation.application.service.ipml.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User testUser;
    private UserReadDto userReadDto;
    private Order testOrder;

    @BeforeEach
    public void setUp() {

        userReadDto = new UserReadDto(
                1,
                "test",
                Role.USER.name(),
                "Test",
                "Test",
                "Test",
                null,
                new BigDecimal("0.00001"));

        testOrder = Order.builder()
                .id(1)
                .users(new HashSet<>())
                .orderDetails(null)
                .build();

        testUser = User.builder()
                .id(1)
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .orders(new HashSet<>())
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();
    }

    @Test
    void checkFindAllEmployeeTest() {
        List<User> users = new ArrayList<>();
        users.add(testUser);
        doReturn(users)
                .when(userRepository).findAllEmployee(Role.USER);
        doReturn(userReadDto)
                .when(userMapper).toDto(any());

        List<UserReadDto> actual = userService.findAllEmployee();

        assertThat(actual).contains(userReadDto);
        verify(userMapper, times(1)).toDto(any());
    }

    @Test
    void checkFindUserByLoginTest() {
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser)
                );
        var userByLogin = userService.findByLogin(testUser.getLogin());
        //then
        Assertions.assertThat(userByLogin).isNotNull();
    }

    @Test
    void checkFindByRole() {
        doReturn(testUser)
                .when(userRepository).findByRole(Role.MANAGER);

        User actual = userService.findByRole(Role.MANAGER);

        assertThat(actual).isEqualTo(testUser);
    }

    @Test
    void checkFindById() {
        doReturn(Optional.of(testUser))
                .when(userRepository).findById(1);
        doReturn(userReadDto)
                .when(userMapper).toDto(testUser);

        UserReadDto actual = userService.findById(1);

        assertThat(actual).isEqualTo(userReadDto);
    }

    @Test
    void checkUpdateUserBalance() {
        Integer userId = testUser.getId();
        doReturn(Optional.of(testUser))
                .when(userRepository).findById(userId);
        doReturn(testUser)
                .when(userRepository).save(testUser);
        BigDecimal expected = new BigDecimal(155);

        userService.updateUserBalance(userId, expected);
        BigDecimal actual = testUser.getBalance();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findUserByLoginReturnUserReadDtoTest() {
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser)
                );
        when(userMapper.toDto(testUser))
                .thenReturn(userReadDto);
        var userByLogin = userService.findUserByLogin(testUser.getLogin());
        //then
        Assertions.assertThat(userByLogin).isNotNull();
    }

    @Test
    void findByIdWithOrderTest() {
        //when
        when(userRepository.findByIdWithOrder(1))
                .thenReturn(testUser);
        when(userMapper.toDto(testUser))
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
        when(userMapper.toDto(testUser))
                .thenReturn(userReadDto);
        var savedUserLikeDto = userService.save(testUser);
        //then
        assertThat(savedUserLikeDto).isNotNull();
    }

    @Test
    public void setOrderToEmployeeTest() {
        //when
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));

        when(orderRepository.findById(testOrder.getId()))
                .thenReturn(Optional.ofNullable(testOrder));

        when(userMapper.toDto(testUser))
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
    public void removeOrderFromEmployeeTest() {
        when(userRepository.findByLogin(testUser.getLogin()))
                .thenReturn(Optional.ofNullable(testUser));

        when(orderRepository.findById(testOrder.getId()))
                .thenReturn(Optional.ofNullable(testOrder));
        when(userMapper.toDto(testUser))
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
        userReadDto = null;
        testOrder = null;
        testUser = null;
    }
}
