package com.example.cabproject.service;

import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.dto.response.UserResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<OrderResponseDto> getAllOrders(){
        List<Order> all = orderRepository.findAll();
        return all.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    //??
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
        Order order = orderRepository.save(modelMapper.map(orderRequestDto, Order.class));
        return  modelMapper.map(order,OrderResponseDto.class);
    }

    public ResponseEntity<String> cancelOrder(long id){
        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order Cancelled");
    }

    public OrderResponseDto updateOrder (Long id,OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setDestination(orderRequestDto.getDestination());
        order.setPickupLocation(orderRequestDto.getPickupLocation());
        order.setOptions(orderRequestDto.getOptions());
        order.setPaymentMethod(orderRequestDto.getPaymentMethod());
        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponseDto.class);
    }








}
