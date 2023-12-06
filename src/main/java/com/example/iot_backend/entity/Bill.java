package com.example.iot_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @Column(name = "id_bill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdDate")
    private Date created;

    @Column(name = "total_price")
    private Double totalPrice;

    private Double pointUsed;
    private Double pointSaved;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "bill" ,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BillDetails> billList = new ArrayList<>();
    public void addBillDetails(BillDetails bill){
        billList.add(bill);
        bill.setBill(this);
    }
}
