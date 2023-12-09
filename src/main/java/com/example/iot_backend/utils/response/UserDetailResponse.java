package com.example.iot_backend.utils.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailResponse {
    private Long id_user;
    private Long id_bill;
    private Date sale_date;
    private Long quantity_product;
    private String name;
}
