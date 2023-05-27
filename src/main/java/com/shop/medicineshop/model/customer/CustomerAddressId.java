package com.shop.medicineshop.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CustomerAddressId implements Serializable {

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="address_id")
    private Integer addressId;

    // constructors, getters, setters
}