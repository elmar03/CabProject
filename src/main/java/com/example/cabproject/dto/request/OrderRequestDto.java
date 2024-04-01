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
   private String pickupLocation;
   private String destination;
   private OrderStatus orderStatus;
   private PaymentMethod paymentMethod;

}
