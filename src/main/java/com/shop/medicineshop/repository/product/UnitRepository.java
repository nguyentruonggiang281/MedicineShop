package com.shop.medicineshop.repository.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Integer> {

    boolean existsByNameAndRankAndSpecifications(String name, Integer rank, Integer specifications);
    Optional<Unit> findByNameAndRankAndSpecifications(String name, Integer rank, Integer specifications);

    @Query("select u from Unit u inner join u.products products where products.id = ?1")
    List<Unit> findByProducts_Id(Integer id);

Unit getUnitByUnitId(Integer unitId);
}
