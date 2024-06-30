package com.example.cabproject.dto.feedback;

import com.example.cabproject.entity.Feedback;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FeedbackResponseDto {
    private Long feedbackId;
    private String feedback;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submissionDate;
    private Integer starNumber;
    private Long userId;
    private Long orderID;
    public FeedbackResponseDto() {
    }


}
