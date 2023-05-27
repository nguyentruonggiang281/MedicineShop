package com.shop.medicineshop.model.customer;

import com.shop.medicineshop.model.account.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    private String email;

    @Column(name = "phone_number", length = 15, unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<CustomerAddress> customerAddresses;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
