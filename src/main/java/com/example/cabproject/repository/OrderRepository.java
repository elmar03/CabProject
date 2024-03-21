package com.example.cabproject.repository;

import com.example.cabproject.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
}
