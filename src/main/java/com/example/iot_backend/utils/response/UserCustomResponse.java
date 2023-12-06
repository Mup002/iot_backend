package com.example.iot_backend.utils.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserCustomResponse {
    private Long id;
    private String phone;
    private Double point;
    private String name;
    private String uid;
    private List<BillDetailCustomResponse> lstBill = new ArrayList();
}
