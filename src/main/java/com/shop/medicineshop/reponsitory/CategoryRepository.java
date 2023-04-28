package com.shop.medicineshop.reponsitory;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentCategory(Category parentCategory);
}
