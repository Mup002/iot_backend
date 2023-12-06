package com.example.iot_backend.repository;

import com.example.iot_backend.entity.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetails, Long> {
    List<BillDetails> findBillDetailsByBillId(Long idBill);
}
