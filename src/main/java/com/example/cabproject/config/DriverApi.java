package com.example.cabproject.config;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.cabproject.dto.CarDto.CarResponseDto;
import com.example.cabproject.dto.CarDto.TaxiResponseDto;
import com.example.cabproject.dto.request.OrderRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Component
@FeignClient(name = "DriverApi",url = "localhost:8081")

public interface DriverApi {

    @PostMapping(value = "/distance/orderReview", consumes = "application/json", produces = "application/json")
    List<TaxiResponseDto> findAvailableCars(OrderRequestDto orderRequestDto);

    @GetMapping("/byID")
    CarResponseDto findByID(long id);

    @GetMapping("/distance/nearest-cars")
    List<CarResponseDto> findNearestCars();


}
