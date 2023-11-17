package com.example.iot_backend.repository;

import com.example.iot_backend.entity.RFID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RFIDRepository extends JpaRepository<RFID, Long> {
    RFID searchRFIDByUid(String uid);
    RFID searchRFIDById(Long id);
}
