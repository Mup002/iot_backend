package com.example.iot_demo.utils.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id_product;
    private String name_product;
    private Double price;
    private Long quantity;
}
