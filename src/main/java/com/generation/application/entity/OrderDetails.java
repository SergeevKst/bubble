package com.generation.application.entity;

import com.generation.application.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetails implements BaseEntity<Integer>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private BigDecimal cost;
    @Column(name = "bubble_count")
    private int bubbleCount;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @CreationTimestamp
    @Column(name="create_date")
    private LocalDate createDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Embedded
    private Address address;
    @OneToOne()
    @PrimaryKeyJoinColumn
    private Order order;
}
