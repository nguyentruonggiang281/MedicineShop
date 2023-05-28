package com.shop.medicineshop.model.store;

import com.shop.medicineshop.model.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "store_product")
public class StoreProduct {

    @EmbeddedId
    private StoreProductId id;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("storeId")
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    private Integer quantity;

}
