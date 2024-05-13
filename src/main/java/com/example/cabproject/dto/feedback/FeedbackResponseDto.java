package com.example.cabproject.dto.feedback;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FeedbackResponseDto {
    private Long feedbackId;
    private String feedback;
    private Date submissionDate;
    private Integer starNumber;
}
