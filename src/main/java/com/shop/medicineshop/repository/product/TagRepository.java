package com.shop.medicineshop.repository.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
   Tag findBySlug(String slug);


}