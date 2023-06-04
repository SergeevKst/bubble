package com.generation.application.repository;

import com.generation.application.entity.Address;
import com.generation.application.entity.Order;
import com.generation.application.entity.OrderDetails;
import com.generation.application.entity.User;
import com.generation.application.model.Role;
import com.generation.application.repository.OrderRepository;
import com.generation.application.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    private Order testOrder;
    private User savedUser;
    private OrderDetails testOrderDetails;
    private Address testAddress;
    private Order savedOrder;

    @BeforeEach
    public void setUp() {
        var testUser = User.builder()
                .login("Test")
                .firstName("Test")
                .lastName("Test")
                .password("test")
                .role(Role.USER)
                .phoneNumber("test")
                .build();
        savedUser = userRepository.save(testUser);

        testAddress = Address.builder()
                .city("test")
                .street("test")
                .apartmentNumber(1)
                .houseNumber(1)
                .build();

        testOrderDetails = OrderDetails.builder()
                .id(10)
                .order(testOrder)
                .address(testAddress)
                .cost(new BigDecimal(100))
                .build();

        testOrder = Order.builder()
                .id(1)
                .users(Set.of(savedUser))
                .orderDetails(testOrderDetails)
                .build();

        savedOrder = orderRepository.save(this.testOrder);
    }

    @Test
    public void saveOrderTest() {
        //then
        Assertions.assertThat(savedOrder).isNotNull();
    }

    @Test
    public void findByIdWithUserTest() {
        //when
        var order = orderRepository.findByIdWithUser(savedOrder.getId());
        //then
        Assertions.assertThat(order.getId()).isEqualTo(savedOrder.getId());
    }

    @Test
    public void findOrderByAddressTest() {
        //when
        Set<Order> orders = orderRepository.findOrderByAddress(
                testAddress.getCity(),
                testAddress.getStreet(),
                testAddress.getHouseNumber(),
                testAddress.getApartmentNumber()
        );
        //then
        Assertions.assertThat(orders).isNotEmpty();
    }

    @Test
    public void findOrderByUserIdTest() {
        //when
        savedUser.setOrders(Set.of(savedOrder));
        Set<Order> ordersSet = orderRepository.findOrderByUserId(savedUser.getId());
        //then
        Assertions.assertThat(ordersSet.isEmpty()).isFalse();
    }

    @AfterEach
    public void cleanUp() {
        testOrderDetails = null;
        testOrder = null;
        testAddress=null;
        savedOrder=null;
        savedUser = null;
    }
}
