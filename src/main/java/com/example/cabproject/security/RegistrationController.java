package com.example.cabproject.security;


import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register/user")
    public ResponseEntity<String>  createUser(@RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User map = modelMapper.map(userRequestDto, User.class);
        myUserRepository.save(map);
        return new ResponseEntity<>("Signed up successfully", HttpStatus.CREATED);
    }


    @GetMapping("/findUserById/{id}")
    public Optional<User> findUser(@PathVariable long id){
        return myUserRepository.findById(id);
    }
}
