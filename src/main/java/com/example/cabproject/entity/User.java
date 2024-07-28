package com.example.cabproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

import java.util.List;
import java.util.Optional;


@Entity
@Data
@Table(name ="\"user\"")
@Builder
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private String role;

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
    @JsonManagedReference
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Feedback> reviews;



}
