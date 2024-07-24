package com.example.cabproject.dto.response;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String surName;
    private String username;
    private String role;
    private int age;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String homeAddress;
    private String cardDetails;
    private String language;
}
