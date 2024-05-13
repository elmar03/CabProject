package com.example.cabproject.dto.response;

import com.example.cabproject.dto.CarDto.CarResponseDto;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class OrderResponseDto {
    
    private String orderId;
    private String customerName;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private LocalDateTime pickupTime;
    private LocalDateTime dropOffTime;
    private double distance;
    private double fare;

    private String driverId;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private CarResponseDto carResponseDto;

}
