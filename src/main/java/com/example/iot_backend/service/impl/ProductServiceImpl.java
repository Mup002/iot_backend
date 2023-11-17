package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.Product;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.service.ProductService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductResponse> getAllProduct() {
        return mapper.productResponseList(StreamSupport.stream(productRepository.findAll().spliterator(),false).collect(Collectors.toList()));
    }

    @Override
    public String updateQuantity(Long id_product, Long quantity) {
        Product product = productRepository.findProductById(id_product);
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        return "done";
    }
}
