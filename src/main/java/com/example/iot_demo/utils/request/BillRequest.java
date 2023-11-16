package com.example.iot_demo.utils.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BillRequest {
    // dinh dang yyyy-MM-dd
    private String createdDate;
    private Long id_user;
    private Double totalPrice;
    private boolean usePoint;
    private  List<ProductCustom> productCustomList = new ArrayList<>();
}
