package com.example.cabproject.config;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }



}
