package com.shop.medicineshop.repository.order;
import com.shop.medicineshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByStoreId(Integer storeId);

}
