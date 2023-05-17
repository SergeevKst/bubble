package com.generation.application.entity;

import com.generation.application.model.OrderStatus;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderdetails")
public class OrderDetails implements BaseEntity<Integer> {

    @Id
    private Integer id;
    private BigDecimal cost;
    private int bubbleCount;
    private LocalDate deliveryDate;
    private OrderStatus status;
    @Embedded
    private Address address;
    @MapsId
    @OneToOne
    private Order order;
}
