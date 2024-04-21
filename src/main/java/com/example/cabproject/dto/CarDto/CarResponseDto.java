package com.example.cabproject.dto.CarDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {

    private Long carId;
    private String carBrand;
    private String carModel;
    private String carColour;
    private Integer carNumber;
    private  Integer carYear;
    private String carSize;
}
