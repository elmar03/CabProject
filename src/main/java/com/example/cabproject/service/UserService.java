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

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    public List<UserResponseDto> getAllUsers(){
//        List<User> all = userRepository.findAll();
//        return all.stream().map(x -> modelMapper.map(x, UserResponseDto.class)).toList();
//    }

    public void userRegistration(UserRequestDto requestDto){
        User newUser = modelMapper.map(requestDto, User.class);
        userRepository.save(newUser);
        ResponseEntity.ok("Registration successful");
    }

    public UserResponseDto findUserById(Long id){
        return userRepository.findUserById(id);
    }

    //login

    public void deleteAccount(long id){
        userRepository.deleteById(id);
        ResponseEntity.ok("Account deleted");
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        User user = userRepository.findById(id).orElseThrow();
        user.setAge(userRequestDto.getAge());
        user.setCardDetails(userRequestDto.getCardDetails());
        user.setGender(userRequestDto.getGender());
        user.setName(userRequestDto.getName());
        user.setEmailAddress(userRequestDto.getEmailAddress());
        user.setHomeAddress(userRequestDto.getHomeAddress());
        user.setLanguage(userRequestDto.getLanguage());
        user.setSurName(userRequestDto.getSurName());
        user.setPhoneNumber(user.getPhoneNumber());
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserResponseDto.class);

    }




}
