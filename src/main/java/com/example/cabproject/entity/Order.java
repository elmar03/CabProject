package com.example.cabproject.entity;

import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String from;
    private String to;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private Options options;

//    @OneToOne(mappedBy = "driver")
//    private Driver driver;

//    @ManyToOne()
//    @JoinColumn(name = "car_id")
//    private Car car;


}
