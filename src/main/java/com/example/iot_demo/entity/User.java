package com.example.iot_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "phoneNumber")
    private String phone;

    @Column(name = "point")
    private Double point;

    @Column(name = "name_user")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rfid")
    private RFID rfid;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bill> billList = new ArrayList<>();

    public void addBill(Bill bill){
        billList.add(bill);
    }
}
