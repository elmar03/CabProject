package com.example.cabproject.service;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.CarDto.TaxiResponseDto;
import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentStatus;
import com.example.cabproject.exceptions.OrderNotFoundException;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
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
    private final DriverApi driverApi;

    OrderRequestDto currentOrderRequestDto;

    public List<TaxiResponseDto> createOrderStep1(OrderRequestDto orderRequestDto) {
        currentOrderRequestDto = orderRequestDto;
        return driverApi.findAvailableCars(orderRequestDto);
    }

    public String createOrderStep2(Long id){
        List<TaxiResponseDto> orderStep1 = createOrderStep1(currentOrderRequestDto);
        List<TaxiResponseDto> list = orderStep1.stream().
                filter(x->x.getCarResponseDto().stream().anyMatch(car->car.getCarId().equals(id))).toList();
        if (!list.isEmpty()) {
        TaxiResponseDto taxiResponseDto = list.get(0);
        Order order = modelMapper.map(currentOrderRequestDto, Order.class);
        User user = userRepository.findById(currentOrderRequestDto.getUserId()).orElseThrow();
        order.setUser(user);
        modelMapper.map(taxiResponseDto.getCarResponseDto().get(0), order);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
        sendOrder(order);
        return "Order created";
        } else {
            return "No matching taxi found for the provided ID";
        }
    }

    public Order sendOrder(Order order) {
        return order;
    }

    public void cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order order1 = order.get();
            if (order1.getStatus() == OrderStatus.PENDING || order1.getStatus() == OrderStatus.ACCEPTED) {
                order1.setStatus(OrderStatus.CANCELLED);
                orderRepository.save(order1);
                ResponseEntity.ok("Order with id " + id + " has been successfully cancelled.");
            } else {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot cancel order with id"
                        + id + "because it's already in progress or completed");
            }
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with the id of " + id + " not found");
        }
    }

    public List<OrderResponseDto> getCompletedOrders() {
        List<Order> finishedOrders = orderRepository.findOrdersByStatus(OrderStatus.COMPLETED);
        return finishedOrders.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public List<OrderResponseDto> getActiveOrders() {
        List<Order> activeOrder = orderRepository.findOrdersByStatus(OrderStatus.ACCEPTED);
        return activeOrder.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public List<OrderResponseDto> getCancelledOrders() {
        List<Order> cancelledOrders = orderRepository.findOrdersByStatus(OrderStatus.CANCELLED);
        return cancelledOrders.stream().map(x -> modelMapper.map(x, OrderResponseDto.class)).toList();
    }

    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with the id of" + id + " not found"));
        if (!validateOrder(orderRequestDto)) {
            throw new IllegalArgumentException("Invalid order request,Please try again");
        }
        order.setDestinationLongitude(orderRequestDto.getDestinationLongitude());
        order.setDestinationLatitude(orderRequestDto.getDestinationLatitude());
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
        if (orderRequestDto.getDestinationLatitude() == null || Double.
                toString(orderRequestDto.getDestinationLatitude()).isEmpty()) {
            return false;
        }
        if (orderRequestDto.getDestinationLongitude() == null || Double.
                toString(orderRequestDto.getDestinationLongitude()).isEmpty()) {
            return false;
        }
        if (orderRequestDto.getPickupLatitude() == null || Double.
                toString(orderRequestDto.getPickupLatitude()).isEmpty()) {
            return false;
        }
        if (orderRequestDto.getPickupLongitude() == null || Double.
                toString(orderRequestDto.getPickupLongitude()).isEmpty()) {
            return false;
        }
        if (orderRequestDto.getPaymentMethod() == null) {
            return false;
        }
        return true;
    }
}
