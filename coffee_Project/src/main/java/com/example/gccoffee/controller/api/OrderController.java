package com.example.gccoffee.controller.api;

import com.example.gccoffee.DTO.OrderDTO;
import com.example.gccoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/api/v1/orders")
    public OrderDTO createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        return orderService.registerOrder(orderDTO);
    }
}
