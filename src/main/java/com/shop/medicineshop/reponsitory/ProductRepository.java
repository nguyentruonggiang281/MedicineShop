package com.shop.medicineshop.reponsitory;

import com.shop.medicineshop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByCategoryId(Integer category_id);
}

