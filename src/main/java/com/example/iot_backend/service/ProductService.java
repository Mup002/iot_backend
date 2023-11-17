package com.example.iot_backend.service;


import com.example.iot_backend.utils.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ProductService {
    List<ProductResponse> getAllProduct();
    String updateQuantity(Long id_product, Long quantity);

    Optional<ProductResponse> getProduct(Long id);
}
