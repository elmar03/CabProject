package com.example.cabproject.dto.CarDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {

    private Long carId;
    private String brand;
    private String model;
    private String colour;
    private Integer number;
    private  Integer year;
    private String size;
    private Double carLat;
    private Double carLong;


}
