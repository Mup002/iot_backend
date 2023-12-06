package com.example.iot_backend.service;

import com.example.iot_backend.utils.response.BillDetailCustomResponse;
import com.example.iot_backend.utils.response.BillDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BillDetailService {
    List<BillDetailResponse> getAllBillDetailByIdBill(Long id_bill);
    List<BillDetailCustomResponse> getAllproductSoldlByDate(String date1, String date2);

    BillDetailCustomResponse getProductByIdInRange(String date1, String date2, Long id);

    List<BillDetailCustomResponse> getProductsByNameInRange(String date1, String date2, String name);

}