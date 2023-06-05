package com.shop.medicineshop.response.order;

import com.shop.medicineshop.model.order.Order;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.response.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements Function<Order, OrderDTO> {

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public OrderDTO apply(Order order) {
        List<OrderItemDTO> orderItems = orderItemMapper.mapOrderItemsToDTO(order.getOrderItems());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new OrderDTO(
                order.getOrderId(),
                orderItems,
                order.getStatus().name(),
                order.getShippingFee(),
                order.getTotal(),
                order.getPayment() == null ? "CASH" : order.getPayment().toString(),
                order.getAddress().toString(),
                order.getCreatedAt()==null ? null : order.getCreatedAt().format(formatter),
                order.getCanceledAt()==null ? null : order.getCanceledAt().format(formatter),
                order.getCompletedAt()==null ? null : order.getCompletedAt().format(formatter),
                order.getDeliveryAt()==null ? null : order.getDeliveryAt().format(formatter)
        );
    }
    public List<OrderDTO> mapToDTO(List<Order> orders) {
        return orders.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }

}
