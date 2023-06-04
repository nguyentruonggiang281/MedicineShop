package com.shop.medicineshop.model.order;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.payment.Payment;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "shipping_fee")
    private Float shippingFee;

    @Column(name = "total", nullable = false)
    private Float total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

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

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}
