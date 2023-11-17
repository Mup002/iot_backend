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

    @ManyToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinTable(
            name = "bill_product",
            joinColumns = @JoinColumn(name = "id_bill"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    public void addProduct(Product product){
        products.add(product);
    }
}
