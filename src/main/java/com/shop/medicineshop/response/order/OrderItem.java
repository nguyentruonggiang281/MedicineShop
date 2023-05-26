package com.shop.medicineshop.response.order;

import com.shop.medicineshop.model.order.Order;
import com.shop.medicineshop.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


//@Entity
//@Table(name = "order_item")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_item_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//    @Column(name = "price")
//    private Double price;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "deleted_at")
//    private LocalDateTime deletedAt;
}
