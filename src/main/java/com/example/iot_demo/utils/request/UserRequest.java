package com.example.iot_demo.utils.request;

import lombok.Data;

@Data
public class UserRequest {
    private String phone;
    private Double point;
    private String name;
    private String uid;
}
