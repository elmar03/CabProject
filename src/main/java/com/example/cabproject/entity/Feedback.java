package com.example.cabproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
