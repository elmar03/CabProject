package com.example.cabproject.repository;

import com.example.cabproject.dto.response.OrderResponseDto;
import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    List<Order> findOrdersByStatus(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o ORDER BY o.orderId DESC")
    Page <Order> findLatestOrders(Pageable pageable);




}
