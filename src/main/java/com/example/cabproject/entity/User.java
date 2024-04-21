package com.example.cabproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
@Table(name ="\"table\"")
public class User {
    @Id
    private Long id;

    private String name;
    private String surName;
    private int age;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String homeAddress;
    private String cardDetails;
    private String language;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
