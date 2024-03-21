package com.example.cabproject.controller;

import com.example.cabproject.dto.UserResponseDto;
import com.example.cabproject.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
@Data
public class UserController {
    private UserService userService;

    @GetMapping("/findAll")
    public List<UserResponseDto> findUsers(){
       return userService.getAllUsers();
    }





}
