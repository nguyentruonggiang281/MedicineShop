package com.shop.medicineshop.repository.product;

import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    @Query("select a from Asset a where a.product.id = ?1")
    List<Asset> findByProduct_Id(Integer id);
}
