package com.example.iot_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "rfids")
@Entity
public class RFID {
    @Id
    @Column(name = "id_rfid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private String uid;

    private boolean isAction ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;


}
