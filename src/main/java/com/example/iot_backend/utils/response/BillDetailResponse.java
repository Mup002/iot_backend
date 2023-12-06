package com.example.iot_backend.utils.response;

import lombok.Data;

@Data
public class BillDetailResponse {
    private Long id_bill;
    private String product_name;
    private Long quantity_sold;
    private Long id_product;
}
