package com.example.cabproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name ="\"Feedback\"")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private  Long feedbackId;
    private String feedback;
    private Date submissionDate;
    private Integer starNumber;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;




}
