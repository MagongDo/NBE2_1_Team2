package com.example.gccoffee.service;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product registerProduct(Product product);
    public List<Product> getProductsByCategory(Category category);
}
