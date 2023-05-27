package com.shop.medicineshop.model.customer;

import com.shop.medicineshop.model.address.Address;
import jakarta.persistence.*;

@Entity
@Table(name="customer_address")
public class CustomerAddress {

    @EmbeddedId
    private CustomerAddressId id;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("customerId")
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne(fetch=FetchType.LAZY)
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name="is_default")
    private boolean isDefault;


    // constructors, getters, setters
}