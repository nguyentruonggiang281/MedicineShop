package com.shop.medicineshop.repository.product;
import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContaining(String name);

    List<Product> findByCategoryId(Integer category_id);

    Product getProductsById(Integer id);

    @Query("select p from Product p inner join p.tags tags where tags.id = ?1")
    List<Product> findByTags_Id(Integer id);


    // List<Product> findProductByTagsIs;

//    @Query("select p from Product p inner join p.tags tags where tags.slug = ?1")
//    List<Product> findByTags_Slug(String slug);


//List<Asset> getAllByAssets(List<Asset> assets);
//List<Product> getProductsBy();
}

