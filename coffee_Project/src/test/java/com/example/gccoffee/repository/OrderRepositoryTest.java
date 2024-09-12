package com.example.gccoffee.repository;

import com.example.gccoffee.entity.OderStatus;
import com.example.gccoffee.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void register() {
        Orders order = Orders.builder()
                .email("lpok2401@naver.com")
                .address("우리집 강아지는 복슬강아지")
                .postcode("7777")
                .oderStatus(OderStatus.ACCEPTED)
                .build();
        orderRepository.save(order);
    }
}

