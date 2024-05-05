package com.example.cabproject.config;

import com.example.cabproject.dto.CarDto.CarResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Component
@FeignClient(name = "DriverApi",url = "localhost:8081")
public interface DriverApi{

    @GetMapping("/car/get-all")
    List<CarResponseDto> getAvailableCars();

    @GetMapping("/byID")
    CarResponseDto findByID(long id);

    @GetMapping("/distance/nearest-cars")
    List<CarResponseDto> findNearestCars();



}
