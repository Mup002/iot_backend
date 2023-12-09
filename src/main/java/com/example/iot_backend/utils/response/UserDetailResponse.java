package com.example.iot_backend.utils.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailResponse {
    private String id_user;
    private Long id;
    private Date sale_date;
    private Long quantity_product;
    private String name;
}
