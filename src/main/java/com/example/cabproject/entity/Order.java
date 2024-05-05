package com.example.cabproject.entity;

import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;

    private Long carId;
    private String brand;
    private String model;
    private String colour;
    private Integer number;
    private  Integer year;
    private String size;
    private Double carLat;
    private Double carLong;
    //private Long userId;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private Options options;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;


}
