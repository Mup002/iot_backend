package com.example.iot_backend.utils.response;

import lombok.Data;

import java.util.Date;

@Data
public class BillResponse {
    private Long id;
    private String createdDate;
    private Double totalPrice;
    private Double usedPoint;
    private String username;
    private Double savedPoint;
}
