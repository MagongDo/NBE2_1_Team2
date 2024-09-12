package com.example.gccoffee.controller;

import com.example.gccoffee.DTO.ProductDTO;
import com.example.gccoffee.entity.Product;
import com.example.gccoffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/delete-product/{id}")
    public String deleteProductConfirmation(@PathVariable Long id, Model model) {
        Product product = productService.read(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "delete-confirm"; // delete-confirm.html 페이지로 이동
        }
        return "redirect:/products"; // 상품이 없으면 목록 페이지로 리디렉션
    }

    // 상품 삭제 처리 (POST 요청)
    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/products"; // 삭제 후 목록 페이지로 리디렉션
    }
    @GetMapping("/edit-product/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.read(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit-product"; // edit-product.html 페이지로 이동
        }
        return "redirect:/products"; // 상품이 없으면 목록 페이지로 리디렉션
    }

    // 상품 수정 (POST 요청)
    @PostMapping("/edit-product/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product updatedProduct) {
        Product existingProduct = productService.read(id);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());

            productService.modify(new ProductDTO(existingProduct));
        }
        return "redirect:/products"; // 수정 후 목록 페이지로 리디렉션
    }

}
