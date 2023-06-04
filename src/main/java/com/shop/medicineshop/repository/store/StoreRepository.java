package com.shop.medicineshop.repository.store;

import com.shop.medicineshop.model.order.Order;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findById(Integer id);

    List<Store> findByAddress_Province(String province);

}
