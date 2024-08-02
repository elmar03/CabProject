package com.example.cabproject;

import com.example.cabproject.config.DriverApi;
import com.example.cabproject.dto.CarDto.CarResponseDto;
import com.example.cabproject.dto.CarDto.TaxiResponseDto;
import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import com.example.cabproject.enums.PaymentMethod;
import com.example.cabproject.enums.Options;
import com.example.cabproject.enums.PaymentStatus;
import com.example.cabproject.exceptions.OrderNotFoundException;
import com.example.cabproject.repository.OrderRepository;
import com.example.cabproject.repository.UserRepository;
import com.example.cabproject.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DriverApi driverApi;

    @InjectMocks
    private OrderService orderService;

    private OrderRequestDto orderRequestDto;
    private Order order;
    private User user;
    private TaxiResponseDto taxiResponseDto;
    private List<TaxiResponseDto> availableCars;
    private OrderResponseDto orderResponseDto;

    @BeforeEach
    public void setUp() {
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1L);
        orderRequestDto.setPickupLatitude(12.34);
        orderRequestDto.setPickupLongitude(56.78);
        orderRequestDto.setDestinationLatitude(87.65);
        orderRequestDto.setDestinationLongitude(43.21);
        orderRequestDto.setOptions(Options.OPTION1);
        orderRequestDto.setPaymentMethod(PaymentMethod.CREDIT_CARD);

        order = new Order();
        order.setOrderId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);

        user = new User();
        user.setUserId(1L);

        taxiResponseDto = new TaxiResponseDto();
        availableCars = List.of(taxiResponseDto);

        orderResponseDto = new OrderResponseDto();
    }

    @Test
    public void testCreateOrderStep1() {
        when(driverApi.findAvailableCars(orderRequestDto)).thenReturn(availableCars);

        List<TaxiResponseDto> result = orderService.createOrderStep1(orderRequestDto);

        verify(driverApi).findAvailableCars(orderRequestDto);
        assertEquals(availableCars, result);
    }

    @Test
    public void testCreateOrderStep2() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(orderRequestDto, Order.class)).thenReturn(order);
        when(modelMapper.map(any(CarResponseDto.class), eq(Order.class))).thenReturn(order);

        // Add a car to available cars for this test
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setCarId(1L);
        taxiResponseDto.setCarResponseDto(List.of(carResponseDto));
        orderService.createOrderStep1(orderRequestDto);

        String result = orderService.createOrderStep2(1L);

        verify(orderRepository).save(order);
        assertEquals("Order created", result);
    }

    @Test
    public void testCreateOrderStep2NoMatchingCar() {
        String result = orderService.createOrderStep2(2L);

        assertEquals("No matching taxi found for the provided ID", result);
    }

    @Test
    public void testSendOrder() {
        when(orderRepository.findLatestOrders(PageRequest.of(0, 1))).thenReturn(new PageImpl<>(List.of(order)));
        when(modelMapper.map(order, OrderResponseDto.class)).thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.sendOrder();

        verify(orderRepository).findLatestOrders(PageRequest.of(0, 1));
        assertEquals(orderResponseDto, result);
    }

    @Test
    public void testCancelOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        ResponseEntity<String> response = orderService.cancelOrder(1L);

        verify(orderRepository).save(order);
        assertEquals("Order with id 1 has been successfully cancelled.", response.getBody());
    }

    @Test
    public void testCancelOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<String> response = orderService.cancelOrder(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order with the id of 1 not found", response.getBody());
    }

    @Test
    public void testGetCompletedOrders() {
        when(orderRepository.findOrdersByStatus(OrderStatus.COMPLETED)).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderResponseDto.class)).thenReturn(orderResponseDto);

        List<OrderResponseDto> result = orderService.getCompletedOrders();

        assertEquals(1, result.size());
        assertEquals(orderResponseDto, result.get(0));
    }

    @Test
    public void testUpdateOrder() throws OrderNotFoundException {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);
        when(modelMapper.map(order, OrderResponseDto.class)).thenReturn(orderResponseDto);

        OrderResponseDto result = orderService.updateOrder(1L, orderRequestDto);

        verify(orderRepository).save(order);
        assertEquals(orderResponseDto, result);
    }

    @Test
    public void testUpdateOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () ->
            orderService.updateOrder(1L, orderRequestDto)
        );
    }
}
