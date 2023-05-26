package com.shop.medicineshop.response.payment;

import com.shop.medicineshop.response.order.OrderDTO;

import java.sql.Date;

public record PaymentDTO(
        int paymentId,
        String paymentMethod,
        String status,
        Date createdAt,
        Date updatedAt,
        OrderDTO order
) {
}
