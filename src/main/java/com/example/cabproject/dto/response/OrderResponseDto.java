package com.example.cabproject.dto.response;

import com.example.cabproject.dto.CarDto.CarResponseDto;
import com.example.cabproject.dto.CarDto.DriverResponseDto;
import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class OrderResponseDto {
    
    private String orderId;
    private Long   userId;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private LocalDateTime pickupTime;
    private LocalDateTime dropOffTime;
    private double distance;

    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private Options options;

    //private CarResponseDto carResponseDto;
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

   // private DriverResponseDto driverResponseDto;

    private Long driverId;
    private  String driverName;
    private  String driverSurname;
    private String driverLanguage;

    private Integer user_review;




}
