package com.example.gccoffee.controller.api;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import com.example.gccoffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Transactional
public class ProductApiController {
    private final ProductService productService;
    @GetMapping("/api/v1/products")
    public List<Product> productList(@RequestParam Optional<Category> category) {

        return category.map(productService::getProductsByCategory).orElse(productService.getProducts());
    }
}
