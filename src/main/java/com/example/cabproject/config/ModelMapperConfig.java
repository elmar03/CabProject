package com.example.cabproject.config;
import com.example.cabproject.dto.feedback.FeedbackRequestDto;
import com.example.cabproject.dto.feedback.FeedbackResponseDto;
import com.example.cabproject.entity.Feedback;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.Hibernate.map;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Feedback, FeedbackResponseDto>() {
            @Override
            protected void configure() {
                map().setOrderID(source.getOrder().getOrderId());
            }
        });

        return modelMapper;
    }



}
