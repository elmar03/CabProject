package com.example.cabproject.entity;

import com.example.cabproject.enums.Options;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@RequiredArgsConstructor
public class Car {
    @Id
    private Long id;

    private String brand;
    private String model;
    private String colour;
    private String registrationPlate;
    @Enumerated(EnumType.STRING)
    private Options options;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany
    @JoinColumn(name = "car")
    private List<Order> orders;

}
