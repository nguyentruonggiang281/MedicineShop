package com.shop.medicineshop.model.store;

import com.shop.medicineshop.model.account.Account;
import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;


//    @Column(name = "address", nullable = false)
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "store_product",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
