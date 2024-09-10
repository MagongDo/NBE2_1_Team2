package com.example.gccoffee.repository;

import com.example.gccoffee.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Orders, Long> {
}
