package com.example.cabproject.repository;

import com.example.cabproject.entity.Driver;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{
}
