package com.shop.medicineshop.reponsitory;

import com.shop.medicineshop.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
