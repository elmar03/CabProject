package com.example.cabproject;

import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.dto.response.UserResponseDto;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.UserRepository;
import com.example.cabproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private UserRequestDto userRequestDto;
    private User user;
    private UserResponseDto userResponseDto;

    @BeforeEach
    public void setUp() {
        // Initialize UserRequestDto with required arguments
        userRequestDto = new UserRequestDto(
                "username", "email@example.com", "password", "John", "Doe", 30, "123 Street"
        );

        // Initialize User with required arguments or setters
        user = new User();
        user.setUserId(1L); // example
        user.setUsername("username");
        user.setEmailAddress("email@example.com");
        user.setPassword("password");
        user.setName("John");
        user.setSurName("Doe");
        user.setAge(30);
        user.setHomeAddress("123 Street");

        // Initialize UserResponseDto with required arguments
        userResponseDto = new UserResponseDto();
        userResponseDto.setUsername("username");
        userResponseDto.setEmailAddress("email@example.com");
        userResponseDto.setName("John");
        userResponseDto.setSurName("Doe");
        userResponseDto.setAge(30);
        userResponseDto.setHomeAddress("123 Street");
    }


    @Test
    public void testUserRegistration() {
        when(modelMapper.map(userRequestDto, User.class)).thenReturn(user);
        doNothing().when(userRepository).save(user);

        userService.userRegistration(userRequestDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindUserById() {
        Long userId = 1L;
        when(userRepository.findUserByUserId(userId)).thenReturn(user);
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);

        UserResponseDto result = userService.findUserById(userId);

        assertNotNull(result);
        assertEquals(userResponseDto, result);
    }

    @Test
    public void testDeleteAccount() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteAccount(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(modelMapper.map(userRequestDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);

        UserResponseDto result = userService.updateUser(userId, userRequestDto);

        assertNotNull(result);
        assertEquals(userResponseDto, result);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        when(userRepository.findUserByEmailAddress(email)).thenReturn(user);

        User result = userService.findByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);
    }
}
