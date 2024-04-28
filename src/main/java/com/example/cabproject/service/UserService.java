package com.example.cabproject.service;

import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.dto.response.UserResponseDto;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void userRegistration(UserRequestDto requestDto){
        User newUser = modelMapper.map(requestDto, User.class);
        userRepository.save(newUser);
        ResponseEntity.ok("Registration successful");
    }

    public UserResponseDto findUserById(Long id){
        User userById = userRepository.findUserById(id);
        return modelMapper.map(userById, UserResponseDto.class);
    }

    //login

    public void deleteAccount(long id){
        userRepository.deleteById(id);
        ResponseEntity.ok("Account deleted");
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        if (userRequestDto == null) {
            throw new IllegalArgumentException("User request cannot be null");
        }
        User user = userRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("User not found with id: " + id));
        modelMapper.map(userRequestDto,user);
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserResponseDto.class);

    }




}
