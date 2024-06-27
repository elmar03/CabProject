package com.example.cabproject.service;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
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


    public void saveFeedback(FeedbackRequestDto feedbackRequestDto){
        Order order = orderRepository.findById(feedbackRequestDto.getOrder_id()).orElseThrow();
        order.setUser_review(feedbackRequestDto.getStarNumber());
        User user = userRepository.findById(feedbackRequestDto.getUser_id()).orElseThrow();
        Feedback feedbackEntity = modelMapper.map(feedbackRequestDto, Feedback.class);
        feedbackEntity.setUser(user);
        feedbackEntity.setOrder(order);
        feedbackRepo.save(feedbackEntity);
        ResponseEntity.ok("Thank you for your feedback");
    }

    public List<Feedback> findFeedbacksByOrderId(Long orderId) {
      return  feedbackRepo.findFeedbacksByOrderId(orderId);

    }
}
