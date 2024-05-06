package com.example.cabproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name ="\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    private String name;
    private String surName;
    private int  age;
    private String emailAddress;
    private String gender;
    private String phoneNumber;
    private String homeAddress;
    private String cardDetails;
    private String language;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
