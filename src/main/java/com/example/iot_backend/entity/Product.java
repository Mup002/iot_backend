package com.example.iot_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "name_product")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Bill> billList = new ArrayList<>();
//
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BillDetails> billList = new ArrayList<>();
    public void addBillDetails(BillDetails bill){
        billList.add(bill);
    }
}
