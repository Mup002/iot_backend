package com.example.iot_backend.repository;


import com.example.iot_backend.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("SELECT b from Bill b Where b.created between :date1 and :date2")
    List<Bill> findBillsByDateRange(@Param("date1") Date date1, @Param("date2") Date date2);
}
