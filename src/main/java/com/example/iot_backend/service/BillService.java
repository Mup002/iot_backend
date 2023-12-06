package com.example.iot_backend.service;

import com.example.iot_backend.entity.Bill;
import com.example.iot_backend.entity.BillDetails;
import com.example.iot_backend.utils.request.BillCustom;
import com.example.iot_backend.utils.request.BillRequest;
import com.example.iot_backend.utils.response.BillDetailResponse;
import com.example.iot_backend.utils.response.BillResponse;
import com.example.iot_backend.utils.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    String createdBill(BillRequest dto, Long id_user);
    String createGuestBill(BillCustom dto);
    List<BillResponse> findByDate(String date1, String date2);
    List<BillResponse> getAllBill();

    List<UserResponse> getUsersByDateOfBill(String date1, String date2);
    List<UserResponse> getUsersByNameInRange(String date1, String date2, String name);
    List<UserResponse> getUsersByPhoneInRange(String date1, String date2, String phone);
    List<UserResponse> getUsersByPhoneAndNameInRange(String date1, String date2, String name, String phone);
}
