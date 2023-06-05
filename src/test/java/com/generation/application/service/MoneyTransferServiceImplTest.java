package com.generation.application.service;

import com.generation.application.dto.OrderDetailsReadDto;
import com.generation.application.dto.OrderReadDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.entity.Address;
import com.generation.application.entity.User;
import com.generation.application.model.OrderStatus;
import com.generation.application.model.Role;
import com.generation.application.service.ipml.MoneyTransferServiceImpl;
import com.generation.application.service.ipml.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceImplTest {

    @InjectMocks
    MoneyTransferServiceImpl moneyTransferService;
    @Mock
    private OrderServiceImpl orderService;
    @Mock
    private UserService userService;
    private final BigDecimal balanceTestBefore = new BigDecimal(1000);
    private final BigDecimal costTest = new BigDecimal(100);
    private final BigDecimal balanceTestAfter = new BigDecimal(900);


    @Test
    void transferTest() {
        User testUser = User.builder()
                .id(1)
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .orders(null)
                .balance(new BigDecimal(900))
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();

        UserReadDto userReadDtoTest = new UserReadDto(
                1,
                "Test",
                "test",
                Role.USER.name(),
                "Test",
                "Test",
                new HashSet<>(),
                new BigDecimal(1000));

        OrderReadDto orderReadDto = new OrderReadDto(
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

        when(userService.findById(1)).thenReturn(userReadDtoTest);
        when(orderService.findById(1)).thenReturn(orderReadDto);
        when(userService.findByRole(Role.OWNER)).thenReturn(testUser);
        doNothing().when(orderService).changeStatusOrder(1, OrderStatus.PAID);
        doNothing().when(userService).updateUserBalance(1, balanceTestBefore.subtract(costTest));
        moneyTransferService.transfer(1, 1);
        assertThat(balanceTestAfter).isEqualTo(testUser.getBalance());
    }
}