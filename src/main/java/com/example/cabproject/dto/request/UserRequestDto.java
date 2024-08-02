package com.example.cabproject.dto.request;

import com.example.cabproject.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDto {
    private String name;
    private String surName;
    private String username;
    private String password;
    private String role;
    private int age;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String homeAddress;
    private String cardDetails;
    private String language;

    public UserRequestDto(String name, String surName, String username, String password, String role, int age, String emailAddress, String gender, String phoneNumber, String homeAddress, String cardDetails, String language) {
        this.name = name;
        this.surName = surName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.cardDetails = cardDetails;
        this.language = language;
    }


    public UserRequestDto(String username, String mail, String password, String john, String doe, int age, String emailAddress) {
    }
}
