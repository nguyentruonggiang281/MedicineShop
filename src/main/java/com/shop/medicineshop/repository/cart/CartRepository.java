package com.shop.medicineshop.repository.cart;

import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByCustomer(Customer customer);

    @Query("select c from Cart c where c.customer.account.id = ?1")
    Optional<Cart> findByCustomer_Account_Id(Integer id);


}