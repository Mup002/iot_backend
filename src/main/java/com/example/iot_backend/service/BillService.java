package com.example.iot_backend.service;

import com.example.iot_backend.utils.request.BillCustom;
import com.example.iot_backend.utils.request.BillRequest;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    String createdBill(BillRequest dto, Long id_user);
    String createGuestBill(BillCustom dto);
}
