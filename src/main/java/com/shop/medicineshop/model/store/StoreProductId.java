package com.shop.medicineshop.model.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StoreProductId implements Serializable {

    @Column(name="store_id")
    private Integer storeId;

    @Column(name="product_id")
    private Integer productId;

    // constructors, getters, setters
}