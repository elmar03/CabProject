package com.example.cabproject.controller;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.exceptions.UserNotFoundException;
import com.example.cabproject.service.FeedbackService;
import com.example.cabproject.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
@Data
public class FeedBackController {

    private final FeedbackService feedbackService;
    private final DriverApi driverApi;

    @PostMapping("/saveFeedback")
    public ResponseEntity<String> saveFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        feedbackService.saveFeedback(feedbackRequestDto);
        return ResponseEntity.ok("Thank you for your feedback");
    }

    @GetMapping("/feedbackByOrderId")
    public List<Feedback> feedbackByOrderId(@RequestParam long orderId){
      return feedbackService.findFeedbacksByOrderId(orderId);
    }



}
