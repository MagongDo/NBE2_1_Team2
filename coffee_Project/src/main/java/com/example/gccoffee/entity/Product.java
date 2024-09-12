package com.example.gccoffee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // Date를 등록, 수정 일시 자동 반영 중요!!
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long price;
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> children = new ArrayList<>();

    public Product(Product product) {
        this.productId = product.productId;
        this.productName = product.productName;
        this.category = product.category;
        this.price = product.price;
        this.description = product.description;

    }
    public void changePrice(Long price) {
        this.price = price;
    }
    public void changeDescription(String description) {
        this.description=description;
    }
    public  void changeProductName(String productName) {
        this.productName = productName;
    }

}
