package com.example.cabproject.entity;

import com.example.cabproject.dto.CarDto.DriverResponseDto;
import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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



    private Integer price;

    private Long driverId;
    private  String driverName;
    private  String driverSurname;
    private String driverLanguage;

    private Integer user_review;


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
    @JsonBackReference
    User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Feedback feedback;

}
