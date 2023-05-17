package com.generation.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String city;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "apartment_number")
    private int apartmentNumber;
}
