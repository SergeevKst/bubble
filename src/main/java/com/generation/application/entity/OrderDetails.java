package com.generation.application.entity;

import com.generation.application.model.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @MapsId
    @OneToOne
    private Order order;
}
