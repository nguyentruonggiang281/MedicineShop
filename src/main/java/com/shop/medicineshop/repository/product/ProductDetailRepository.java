package com.shop.medicineshop.repository.product;

import com.shop.medicineshop.model.product.ProductDetail;
import com.shop.medicineshop.response.product.ProductDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    ProductDetail getProductDetailByProductId(Integer productId);

}
