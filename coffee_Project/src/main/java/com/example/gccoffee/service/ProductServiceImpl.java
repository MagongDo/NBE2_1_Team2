package com.example.gccoffee.service;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import com.example.gccoffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
private final ProductRepository productRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAlls();
    }

    @Override
    public Product registerProduct(Product product) {
        Product products=Product.builder().productName(product.getProductName())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        log.info(product.getProductName());
        log.info(product.getCategory());
        log.info(products);
        return productRepository.save(products);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

}
