package com.shop.medicineshop.mapper;

import com.shop.medicineshop.model.cart.CartItem;
import com.shop.medicineshop.model.order.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public OrderItem toOrderItem(CartItem cartItem) {
        return OrderItem.builder()
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .unit(cartItem.getUnit())
                .price(cartItem.getPrice())
                .build();
    }

}
