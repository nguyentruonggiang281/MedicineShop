package com.shop.medicineshop.reponsitory;

import com.shop.medicineshop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
