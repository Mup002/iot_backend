package com.example.iot_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @Column(name = "name_category")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
