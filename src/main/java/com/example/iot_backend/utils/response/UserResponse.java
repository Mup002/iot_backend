package com.example.iot_backend.utils.response;

import lombok.Data;

@Data
public class UserResponse {
    private String phone;
    private Double point;
    private String name;
    private String uid;
}
