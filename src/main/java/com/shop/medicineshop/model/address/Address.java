package com.shop.medicineshop.model.address;

import com.shop.medicineshop.model.customer.CustomerAddress;
import com.shop.medicineshop.model.store.Store;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "ward", nullable = false)
    private String ward;

    @Column(name = "street", nullable = false)
    private String street;

}
