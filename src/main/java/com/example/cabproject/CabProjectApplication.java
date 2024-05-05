package com.example.cabproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CabProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabProjectApplication.class, args);
    }

}
