package com.example.gccoffee.service;

import com.example.gccoffee.DTO.OrderDTO;
import com.example.gccoffee.DTO.OrderItemDTO;
import com.example.gccoffee.Exception.OrderException;
import com.example.gccoffee.entity.OrderItem;
import com.example.gccoffee.entity.Orders;
import com.example.gccoffee.entity.Product;
import com.example.gccoffee.repository.OrderItemRepository;
import com.example.gccoffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderDTO registerOrder(OrderDTO orderDTO) {
        Orders order=orderDTO.toEntity();
        orderRepository.save(order);
        List<OrderItemDTO> orderItemList=orderDTO.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderItem.setOrdersId(order.getOrdersId());
            OrderItem orderItem1=orderItem.toEntity();
            orderItemRepository.save(orderItem1);
        });
        return new OrderDTO(order.getEmail(),order.getAddress(),order.getPostcode(),orderItemList);
    }



}
