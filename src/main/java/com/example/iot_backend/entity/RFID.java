package com.example.iot_backend.entity;

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

    private boolean currentStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;
    public RFID (){
        this.currentStatus = false;
    }
}
