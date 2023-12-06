package com.example.iot_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "billdetails")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_billdetails")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private String product_name;

    private Long quantity_sold;

//    private Double pointUse;
}
