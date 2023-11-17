package com.example.iot_backend.service.impl;

import com.example.iot_backend.entity.RFID;
import com.example.iot_backend.entity.User;
import com.example.iot_backend.repository.RFIDRepository;
import com.example.iot_backend.service.RfidService;
import com.example.iot_backend.utils.mapper;
import com.example.iot_backend.utils.request.RfidRequest;
import com.example.iot_backend.utils.response.RfidResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RfidServiceImpl implements RfidService {
    private final RFIDRepository rfidRepository;
    @Override
    public String updateAction(Long id, User user) {
        RFID rfid = rfidRepository.searchRFIDById(id);
        if(rfid.isAction() == false){
            rfid.setAction(true);
            rfid.setUser(user);
        }
        return "done";
    }

    @Override
    public List<RfidResponse> getCardIsntAction() {
        List<RFID> action = rfidRepository.findAll();
        List<RFID> isnt = new ArrayList<>();
        for(RFID r : action){
            if(r.isAction() ==  false){
                isnt.add(r);
            }
        }
        return mapper.rfidResponses(isnt);
    }
}
