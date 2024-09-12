package com.example.gccoffee.DTO;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Category category;
    private Long price;
    private String description;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
    public Product toEntity(){
        return Product.builder().productId(productId)
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}
