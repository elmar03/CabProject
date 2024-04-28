package com.example.cabproject.dto.request;

import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderRequestDto {

   private String customerName;
   private Options options;
   private Double pickupLatitude;
   private Double pickupLongitude;
   private Double destinationLatitude;
   private Double destinationLongitude;
   private PaymentMethod paymentMethod;

}
