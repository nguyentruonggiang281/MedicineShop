package com.shop.medicineshop.response.order;

import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.payment.Payment;
import com.shop.medicineshop.response.customer.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
        Long orderId,
        CustomerDTO customer,
        String status,

          BigDecimal shippingFee,

         BigDecimal total,

         Payment payment,

//    @ManyToOne
//    @JoinColumn(name = "coupon_id")
//    private Coupon coupon;

//    @ManyToOne
//    @JoinColumn(name = "affiliate_id")
//    private Affiliate affiliate;

         LocalDateTime createdAt,


         LocalDateTime canceledAt,

         LocalDateTime completedAt,
         LocalDateTime deliveryAt

) {
}
