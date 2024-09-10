package com.example.gccoffee.controller;

import com.example.gccoffee.entity.Product;
import com.example.gccoffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = productService.getProducts();
        model.addAttribute("products", products);
        return "product-list";
    }
    @GetMapping("new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(@ModelAttribute Product product) {
        productService.registerProduct(product);
        return "redirect:/products";
    }

}
