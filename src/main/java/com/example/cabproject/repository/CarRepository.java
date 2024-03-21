package com.example.cabproject.repository;

import com.example.cabproject.entity.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

}
