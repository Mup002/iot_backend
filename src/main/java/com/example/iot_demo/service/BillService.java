package com.example.iot_demo.service;

import com.example.iot_demo.utils.request.BillRequest;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    String createdBill(BillRequest dto, Long id_user);
}
