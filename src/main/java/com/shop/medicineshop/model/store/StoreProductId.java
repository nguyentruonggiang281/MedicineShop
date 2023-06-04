package com.shop.medicineshop.model.store;

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
public class StoreProductId implements Serializable {

    @Column(name="store_id")
    private Integer storeId;

    @Column(name="product_id")
    private Integer productId;

}