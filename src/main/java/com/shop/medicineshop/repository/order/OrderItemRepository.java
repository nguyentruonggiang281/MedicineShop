package com.shop.medicineshop.repository.order;

import com.shop.medicineshop.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
