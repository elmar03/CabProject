//package com.example.cabproject.config;
//
//import feign.RequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FeignClientConfig {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public FeignClientConfig(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Bean
//    public RequestInterceptor bearerTokenRequestInterceptor() {
//        return requestTemplate -> {
//            String token = jwtTokenProvider.getToken();
//            if (token != null && !token.isEmpty()) {
//                requestTemplate.header("Authorization", "Bearer " + token);
//            } else {
//                System.out.println("No JWT token available for the request");
//            }
//        };
//    }
//}