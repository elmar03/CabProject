package com.example.cabproject.service;

import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.exceptions.OrderNotFoundException;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

@Transactional
public OrderResponseDto createOrder(OrderRequestDto orderRequestDto){
    if (!validateOrder(orderRequestDto)) {
        throw new IllegalArgumentException("Invalid order request,Please try again");
    }
    Order order = modelMapper.map(orderRequestDto, Order.class);
    Order savedOrder = orderRepository.save(order);
    log.info("Order created: {}", savedOrder);
    return modelMapper.map(savedOrder, OrderResponseDto.class);
}

    public void cancelOrder(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            Order order1 = order.get();
            if(order1.getStatus()== OrderStatus.PENDING || order1.getStatus()==OrderStatus.ACTIVE){
                orderRepository.deleteById(id);
                ResponseEntity.ok("Order with id " + id + " has been successfully cancelled.");
            }else {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel order with id"
                        + id + "because it's already in progress or completed");
            }
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with the id of " + id + " not found");
        }

    }

    public List<OrderResponseDto> getFinishedOrdersHistory(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Order> finishedOrders = orderRepository.findByUserAndStatus(user, OrderStatus.COMPLETED);
        return finishedOrders.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public List<OrderResponseDto> getActiveOrder(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Order> activeOrder = orderRepository.findByUserAndStatus(user, OrderStatus.ACTIVE);
        return activeOrder.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public List<OrderResponseDto> getCancelledOrders(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Order> cancelledOrders = orderRepository.findByUserAndStatus(user, OrderStatus.CANCELLED);
        return cancelledOrders.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public OrderResponseDto updateOrder (Long id,OrderRequestDto orderRequestDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("Order with the id of" + id + " not found"));
        if (!validateOrder(orderRequestDto)) {
            throw new IllegalArgumentException("Invalid order request,Please try again");
        }
        order.setDestination(orderRequestDto.getDestination());
        order.setPickupLocation(orderRequestDto.getPickupLocation());
        order.setOptions(orderRequestDto.getOptions());
        order.setPaymentMethod(orderRequestDto.getPaymentMethod());
        Order save = orderRepository.save(order);
        return modelMapper.map(save, OrderResponseDto.class);
    }


    private boolean validateOrder(OrderRequestDto orderRequestDto) {
        if (orderRequestDto.getCustomerName() == null || orderRequestDto.getCustomerName().isEmpty()) {
            return false;
        }
        if (orderRequestDto.getOptions() == null) {
            return false;
        }
        if (orderRequestDto.getPickupLocation() == null || orderRequestDto.getPickupLocation().isEmpty()) {
            return false;
        }
        if (orderRequestDto.getDestination() == null || orderRequestDto.getDestination().isEmpty()) {
            return false;
        }
        if (orderRequestDto.getPaymentMethod() == null) {
            return false;
        }
        return true;
    }










}
