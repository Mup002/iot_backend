package com.example.iot_demo.repository;

import com.example.iot_demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
    Product findProductById(Long id);
}
