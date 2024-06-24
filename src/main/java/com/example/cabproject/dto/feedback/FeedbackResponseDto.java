package com.example.cabproject.dto.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FeedbackResponseDto {
    private Long feedbackId;
    private String feedback;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
//
//    private Date submissionDate;
    private Integer starNumber;
    private Long userId;
    private Long orderID;
}
