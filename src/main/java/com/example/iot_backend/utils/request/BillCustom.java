package com.example.iot_backend.utils.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BillCustom {
    private String createdDate;
    private Double totalPrice;
    private List<ProductCustom> productCustomList = new ArrayList<>();
}
