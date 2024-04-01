package com.example.cabproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDto {
    private String name;
    private String surName;
    private int age;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String homeAddress;
    private String cardDetails;
    private String language;
}
