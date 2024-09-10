package com.example.gccoffee.DTO;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private Category category;
    private Long price;
    private String description;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
    public Product toEntity(){
        return Product.builder().productId(productId)
                .category(category)
                .price(price)
                .description(description)
                .build();
    }
}
