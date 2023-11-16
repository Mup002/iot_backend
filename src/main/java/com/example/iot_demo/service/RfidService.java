package com.example.iot_demo.service;

import com.example.iot_demo.utils.request.RfidRequest;
import org.springframework.stereotype.Service;

@Service
public interface RfidService {
    String updateAction(Long id);
}
