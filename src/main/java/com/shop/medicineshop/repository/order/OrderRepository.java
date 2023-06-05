package com.shop.medicineshop.repository.order;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByStoreId(Integer storeId);

    @Query("select o from Order o where o.customer.account.id = ?1")
    List<Order> findByCustomer_Account_Id(Integer id);

    @Query("select o from Order o where o.store.account.id = ?1")
    List<Order> findByStore_Account_Id(Integer id);



}
