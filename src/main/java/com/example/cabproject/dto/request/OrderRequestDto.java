package com.example.cabproject.dto.request;

import com.example.cabproject.entity.User;
import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
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

   private Long userId;

}
