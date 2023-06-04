package com.generation.application.repository;

import com.generation.application.entity.Order;
import com.generation.application.entity.User;
import com.generation.application.model.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Set;

public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser=User.builder()
                .id(1)
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();
    }

    @Test
    public void saveUserTest() {
        //when
        var savedUser = userRepository.save(testUser);
        //then
        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void findByLoginTest() {
        //when
        userRepository.save(testUser);
        var test = userRepository.findByLogin("Test").orElseThrow();
        //then
        Assertions.assertThat(test.getLogin()).isEqualTo("Test");
    }

    @Test
    public void findByIdWithOrderTest() {
        //given
        Set<User> userSet = Set.of(testUser);
        var testOrder = Order.builder()
                .id(1)
                .users(userSet)
                .orderDetails(null)
                .build();
        Set<Order> orderSet = Set.of(testOrder);
        testUser.setOrders(orderSet);
        //when
        var user2 = userRepository.findByIdWithOrder(1);
        //then
        Assertions.assertThat(user2).isNotNull();
    }

    @AfterEach
    public void cleanUp() {
        testUser = null;
    }
}
