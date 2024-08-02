package com.example.cabproject.service;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.FeedbackRepo;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {

    private final ModelMapper modelMapper;
    private final DriverApi driverApi;
    private final FeedbackRepo feedbackRepo;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public ResponseEntity<String> saveFeedback(FeedbackRequestDto feedbackRequestDto){
        Order order = orderRepository.findById(feedbackRequestDto.getOrder_id()).orElseThrow();
        order.setUser_review(feedbackRequestDto.getStarNumber());
        User user = userRepository.findById(feedbackRequestDto.getUser_id()).orElseThrow();
        Feedback feedbackEntity = modelMapper.map(feedbackRequestDto, Feedback.class);
        feedbackEntity.setUser(user);
        feedbackEntity.setOrder(order);
        feedbackRepo.save(feedbackEntity);
        ResponseEntity.ok("Thank you for your feedback");
        return null;
    }

    public List<FeedbackResponseDto> findFeedbacksByOrderId(Long orderId) {
        List<Feedback> feedbacksByOrderId = feedbackRepo.findFeedbacksByOrderId(orderId);
        return feedbacksByOrderId.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackResponseDto.class))
                .collect(Collectors.toList());
    }
}
