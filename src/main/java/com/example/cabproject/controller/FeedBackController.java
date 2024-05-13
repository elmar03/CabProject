package com.example.cabproject.controller;

import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.exceptions.UserNotFoundException;
import com.example.cabproject.service.FeedbackService;
import com.example.cabproject.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedback")
@Data
public class FeedBackController {

    private final FeedbackService feedbackService;

    @PostMapping("/saveFeedback")
    public ResponseEntity<String> saveFeedback(FeedbackRequestDto feedbackRequestDto) throws UserNotFoundException {
        feedbackService.saveFeedback(feedbackRequestDto);
        return ResponseEntity.ok("Thank you for your feedback");
    }

}
