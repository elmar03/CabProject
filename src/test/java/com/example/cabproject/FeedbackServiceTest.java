package com.example.cabproject;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.FeedbackRepo;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
import com.example.cabproject.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DriverApi driverApi;

    @Mock
    private FeedbackRepo feedbackRepo;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private FeedbackRequestDto feedbackRequestDto;
    private Feedback feedback;
    private Order order;
    private User user;
    private FeedbackResponseDto feedbackResponseDto;

    @BeforeEach
    public void setUp() {
        feedbackRequestDto = new FeedbackRequestDto();
        feedbackRequestDto.setOrder_id(1L);
        feedbackRequestDto.setUser_id(1L);
        feedbackRequestDto.setStarNumber(5);

        order = new Order();
        order.setOrderId(1L);  // Corrected method name

        user = new User();
        user.setUserId(1L);

        feedback = new Feedback();
        feedback.setOrder(order);
        feedback.setUser(user);

        feedbackResponseDto = new FeedbackResponseDto();
    }

    @Test
    public void testSaveFeedback() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(feedbackRequestDto, Feedback.class)).thenReturn(feedback);
        when(feedbackRepo.save(feedback)).thenReturn(feedback);

        ResponseEntity<String> response = feedbackService.saveFeedback(feedbackRequestDto);

        verify(orderRepository).findById(1L);
        verify(userRepository).findById(1L);
        verify(modelMapper).map(feedbackRequestDto, Feedback.class);
        verify(feedbackRepo).save(feedback);
        assertEquals("Thank you for your feedback", response.getBody());
    }

    @Test
    public void testFindFeedbacksByOrderId() {
        when(feedbackRepo.findFeedbacksByOrderId(1L)).thenReturn(List.of(feedback));
        when(modelMapper.map(feedback, FeedbackResponseDto.class)).thenReturn(feedbackResponseDto);

        List<FeedbackResponseDto> feedbacks = feedbackService.findFeedbacksByOrderId(1L);

        verify(feedbackRepo).findFeedbacksByOrderId(1L);
        assertNotNull(feedbacks);
        assertEquals(1, feedbacks.size());
        assertEquals(feedbackResponseDto, feedbacks.get(0));
    }

    @Test
    public void testSaveFeedbackOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                feedbackService.saveFeedback(feedbackRequestDto)
        );

        assertEquals("Order not found", exception.getMessage());
    }

    @Test
    public void testSaveFeedbackUserNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                feedbackService.saveFeedback(feedbackRequestDto)
        );

        assertEquals("User not found", exception.getMessage());
    }
}
