package com.shop.medicineshop.repository.store;

import com.shop.medicineshop.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findById(Integer id);
}
