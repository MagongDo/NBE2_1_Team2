package com.example.gccoffee.DTO;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.OrderItem;
import com.example.gccoffee.entity.Orders;
import com.example.gccoffee.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

public class OrderItemDTO {
    private Long ordersId;
    private Long productId;
    private Category category;
    private Long price;
    private Long quantity;

    public OrderItemDTO(OrderItem orderItem) {
        this.ordersId = orderItem.getOrders().getOrdersId();
        this.productId = orderItem.getProduct().getProductId();
        this.category = orderItem.getCategory();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
    public OrderItem toEntity()
    {
        Product product=Product.builder().productId(productId).build();
        Orders  orders=Orders.builder().ordersId(ordersId).build();
        return OrderItem.builder()
                .orders(orders)
                .product(product)
                .category(category)
                .price(price)
                .quantity(quantity)
                .build();
    }

}
