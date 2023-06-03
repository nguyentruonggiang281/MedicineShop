package com.shop.medicineshop.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressId implements Serializable {

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="address_id")
    private Integer addressId;

    // constructors, getters, setters
}