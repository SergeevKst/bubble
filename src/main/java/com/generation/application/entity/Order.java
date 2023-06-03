package com.generation.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order implements BaseEntity<Integer>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "orders",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderDetails orderDetails;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
