package com.shop.medicineshop.response.order;

import com.shop.medicineshop.model.order.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderItemMapper implements Function<OrderItem, OrderItemDTO> {
    @Override
    public OrderItemDTO apply(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getUnit().getName()
        );
    }
    public List<OrderItemDTO> mapOrderItemsToDTO(List<OrderItem> items) {
        return items.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
