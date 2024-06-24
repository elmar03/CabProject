package com.example.cabproject.config;

import com.example.cabproject.dto.CarDto.CarResponseDto;
import com.example.cabproject.dto.CarDto.TaxiResponseDto;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
import com.example.cabproject.dto.request.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Component
@FeignClient(name = "DriverApi",url = "localhost:8081")
public interface DriverApi{

//    @PostMapping("/distance/orderReview")
//    List<TaxiResponseDto> getAvailableCars(OrderRequestDto orderRequestDto);
    @GetMapping("/distance/orderReview")
    List<TaxiResponseDto> findAvailableCars(OrderRequestDto orderRequestDto);

    @GetMapping("/byID")
    CarResponseDto findByID(long id);

    @GetMapping("/distance/nearest-cars")
    List<CarResponseDto> findNearestCars();


    @PostMapping("/feedBack/saveFeedback")
    FeedbackResponseDto saveFeedback();



}
