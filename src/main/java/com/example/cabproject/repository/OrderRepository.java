package com.example.cabproject.repository;

import com.example.cabproject.entity.Order;
import com.example.cabproject.entity.User;
import com.example.cabproject.enums.OrderStatus;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

    List<Order> findByUserAndStatus(User user, OrderStatus orderStatus);
}
