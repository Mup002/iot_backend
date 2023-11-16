package com.example.iot_demo.service.impl;

import com.example.iot_demo.entity.RFID;
import com.example.iot_demo.repository.RFIDRepository;
import com.example.iot_demo.service.RfidService;
import com.example.iot_demo.utils.request.RfidRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RfidServiceImpl implements RfidService {
    private final RFIDRepository rfidRepository;
    @Override
    public String updateAction(Long id) {
        RFID rfid = rfidRepository.searchRFIDById(id);
        if(rfid.isAction() == false){
            rfid.setAction(true);
        }
        return "done";
    }
}
