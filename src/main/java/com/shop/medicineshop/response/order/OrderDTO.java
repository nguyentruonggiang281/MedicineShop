package com.shop.medicineshop.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderDTO(
        Integer orderId,
        List<OrderItemDTO> orderItems,
        String status,
        Float shippingFee,
        Float total,
        String payment,
        String address,

        String createdAt,

        String canceledAt,

        String completedAt,

        String deliveryAt
) {
}
