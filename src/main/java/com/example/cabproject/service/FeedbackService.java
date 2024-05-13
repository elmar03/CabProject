package com.example.cabproject.service;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.exceptions.UserNotFoundException;
import com.example.cabproject.repository.FeedbackRepo;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {

    private final ModelMapper modelMapper;
    private final DriverApi driverApi;
    private final FeedbackRepo feedbackRepo;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public void saveFeedback(FeedbackRequestDto feedbackRequestDto) throws UserNotFoundException {

        Order order = orderRepository.findById(feedbackRequestDto.getOrderID()).orElseThrow();
        if(order.getStatus()== OrderStatus.COMPLETED){
            Feedback feedbackEntity = modelMapper.map(feedbackRequestDto, Feedback.class);
            Optional<User> optionalUser = userRepository.findById(feedbackRequestDto.getUserId());
            User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found"));
            feedbackEntity.setUser(user);
            feedbackRepo.save(feedbackEntity);
            ResponseEntity.ok("Thank you for your feedback");
        } else {
            ResponseEntity.ok("You can't send feedback, for Active order.");

        }
    }

}
