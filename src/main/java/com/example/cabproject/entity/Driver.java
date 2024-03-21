package com.example.cabproject.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Driver {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String language;

    @OneToOne(mappedBy = "driver")
    private Car car;




}
