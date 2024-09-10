package com.example.gccoffee.repository;

import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p")
    List<Product> findAlls();
    @Query("SELECT p FROM Product p WHERE p.category=:category")
    List<Product> findByCategory(@Param("category") Category category);
}
