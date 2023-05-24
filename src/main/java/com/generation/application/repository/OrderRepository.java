package com.generation.application.repository;

import com.generation.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o left join fetch o.users where o.id=?1")
    Order findByIdWithUser(Integer id);

    @Query("select o from Order o where o.orderDetails.address.city=?1 and o.orderDetails.address.street=?2 and " +
            "o.orderDetails.address.houseNumber=?3 and o.orderDetails.address.apartmentNumber=?4")
    Set<Order> findOrderByAddress(String city, String street, int houseNumber, int apartmentNumber);


    @Query("select u.orders from User u where u.id=?1")
    Set<Order> findOrderByUserId(Integer id);

}
