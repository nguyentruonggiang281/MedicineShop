package com.shop.medicineshop.repository.cart;

import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("select c from CartItem c where c.product.id = ?1 and c.unit.unitId = ?2 and c.cart.id = ?3")
    Optional<CartItem> findByProduct_IdAndUnit_UnitIdAndCart_Id(Integer id, Integer unitId, Integer id1);
   
    Optional<CartItem> findCartItemByCartItemId(Integer id);

    @Query("select c from CartItem c where c.cartItemId = ?1 and c.cart.id = ?2")
    Optional<CartItem> findByCartItemIdAndCart_Id(Integer cartItemId, Integer id);

    @Modifying
    @Transactional
    @Query("delete from CartItem c where c.cartItemId = ?1")
    void deleteByCartItemId(Integer cartItemId);

    List<CartItem> findAllByCart_Id(Integer id);
//    @Query("select c from CartItem c where c.cartItemId = ?1 and c.cart = ?2")
//    Optional<CartItem> findByCartItemIdAndCart(Integer cartItemId, Cart cart);


//    Optional<CartItem> findCartItemByCartAndCartItemId(Cart cart, Integer id);

}