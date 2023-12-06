package com.example.iot_backend.utils.response;

import lombok.Data;

@Data
public class BillDetailCustomResponse {
    private Long id_product;
    private String product_name;
    private Long quantity_sold;
    private Long quantity_remain;
    private Double price_sold;
}
