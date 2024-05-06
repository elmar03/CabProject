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
    private final OrderService orderService;

    public void userRegistration(UserRequestDto requestDto){
        User newUser = modelMapper.map(requestDto, User.class);
        userRepository.save(newUser);
        ResponseEntity.ok("Registration successful");
    }

    public UserResponseDto findUserById(Long userId){
        User userById = userRepository.findByUserId(userId);
        return modelMapper.map(userById, UserResponseDto.class);
    }

    public void deleteAccount(long userId){
        userRepository.deleteById(userId);
        ResponseEntity.ok("Account deleted");
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto){
        if (userRequestDto == null) {
            throw new IllegalArgumentException("User request cannot be null");
        }
        User user = userRepository.findById(userId).orElseThrow(()->
                new NoSuchElementException("User not found with id: " + userId));
        modelMapper.map(userRequestDto,user);
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserResponseDto.class);

    }






}
