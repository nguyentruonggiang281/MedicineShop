package com.shop.medicineshop.model.order;

import com.shop.medicineshop.model.payment.Payment;
import com.shop.medicineshop.model.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "shipping_fee", precision = 18, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "total", nullable = false, precision = 18, scale = 2)
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

//    @ManyToOne
//    @JoinColumn(name = "coupon_id")
//    private Coupon coupon;

//    @ManyToOne
//    @JoinColumn(name = "affiliate_id")
//    private Affiliate affiliate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "delivery_at")
    private LocalDateTime deliveryAt;

//    @OneToMany(mappedBy = "order")
//    private List<OrderDetail> orderDetails;
}
