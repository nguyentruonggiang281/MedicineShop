package com.shop.medicineshop.repository;

import com.shop.medicineshop.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    List<Category> findByParentCategory(Category parentCategory);
    Category findByName(String name);

    Category getCategoriesById(Integer id);

    List<Category> findAllByParentId(Integer parentId);
}
