package com.example.cabproject.controller;

import com.example.cabproject.dto.CarDto.TaxiResponseDto;
import com.example.cabproject.dto.request.OrderRequestDto;
import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.exceptions.CarNotFoundException;
import com.example.cabproject.exceptions.OrderNotFoundException;
import com.example.cabproject.exceptions.UserNotFoundException;
import com.example.cabproject.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Data
public class OrderController {

    private final OrderService orderService;

    @PostMapping ("/sendRequest")
    public List<TaxiResponseDto> getCars(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrderStep1(orderRequestDto);
    }

//    @PostMapping("/createOrder")
//    public String createOrder(@RequestParam Long id) throws CarNotFoundException, UserNotFoundException {
//      return orderService.createOrderStep2( id);
//    }
    @GetMapping("/completedOrders")
    public List<OrderResponseDto> getCompletedOrders(){
        return orderService.getCompletedOrders();
    }

    @GetMapping("/cancelledOrders")
    public List<OrderResponseDto> getCancelledOrders(){
        return orderService.getCancelledOrders();
    }

    @GetMapping("/activeOrders")
    public List<OrderResponseDto> getActiveOrders(){
        return orderService.getActiveOrders();
    }

    @PutMapping("/cancelOrder{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable long id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order cancelled");
    }

    @PutMapping("/updateOrder")
    public OrderResponseDto updateOrder(Long id,@RequestBody OrderRequestDto orderRequestDto)
            throws OrderNotFoundException {
       return orderService.updateOrder(id,orderRequestDto);
    }

    @PostMapping("/sendOrder")
    public Order sendOrder(Order order){
        return orderService.sendOrder(order);
    }



}
