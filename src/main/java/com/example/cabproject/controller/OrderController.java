package com.example.cabproject.controller;

import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.dto.response.UserResponseDto;
import com.example.cabproject.exceptions.OrderNotFoundException;
import com.example.cabproject.service.OrderService;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Data
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
      return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/completedOrders")
    public List<OrderResponseDto> getCompletedOrders(Long id){

        return orderService.getFinishedOrdersHistory(id);
    }
    @GetMapping("/cancelledOrders")
    public List<OrderResponseDto> getCancelledOrders(Long id){
        return orderService.getCancelledOrders(id);
    }
    @GetMapping("/activeOrder")
    public List<OrderResponseDto> getActiveOrder(Long id){
        return orderService.getActiveOrder(id);
    }

    @DeleteMapping("/cancel{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable long id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order cancelled");
    }

    @PutMapping("/updateOrder")
    public OrderResponseDto updateOrder(Long id,@RequestBody OrderRequestDto orderRequestDto)
            throws OrderNotFoundException {
       return orderService.updateOrder(id,orderRequestDto);
    }







}
