package com.example.iot_backend.utils.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCustomResponse {
    private Long id_product;
    private String name_product;
    private Long quantity_sold;
    private Date sale_date;
}
