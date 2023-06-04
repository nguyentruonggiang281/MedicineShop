package com.shop.medicineshop.repository.store;

import com.shop.medicineshop.model.store.StoreProduct;
import com.shop.medicineshop.model.store.StoreProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, StoreProductId> {
    Optional<StoreProduct> findByProductIdAndStoreId(Integer productId, Integer storeId);
}
