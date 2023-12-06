package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.Product;
import com.example.iot_backend.repository.ProductRepository;
import com.example.iot_backend.service.BillService;
import com.example.iot_backend.service.ProductService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.response.BillResponse;
import com.example.iot_backend.utils.response.ProductCustomResponse;
import com.example.iot_backend.utils.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<ProductResponse> getProduct(Long id) {
        Product product = productRepository.findProductById(id);
        return Optional.of(mapper.productToProductResponse(product));
    }

    @Override
    public List<ProductCustomResponse> getProductSold(String saleDate) {

        return null;
    }
}
