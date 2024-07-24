package com.example.cabproject.controller;

import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.dto.response.UserResponseDto;
import com.example.cabproject.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Data
public class UserController {

    private final UserService userService;

    @GetMapping("/findUserById/{id}")
    public UserResponseDto findUser(@PathVariable long id){
        return userService.findUserById(id);
    }

    @PutMapping("/update{id}")
    public UserResponseDto updateUser(@PathVariable long id, @RequestBody UserRequestDto dto){
     return userService.updateUser(id, dto);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        userService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }

//    @PostMapping("/signUp")
//    public ResponseEntity<String> signUp(@RequestBody UserRequestDto userRequestDto){
//        userService.userRegistration(userRequestDto);
//        return ResponseEntity.ok("Registration successful");
//    }



}
