package com.shop.medicineshop.model.product;

import com.shop.medicineshop.model.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "sold")
    private Integer sold;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "featured", nullable = false)
    private boolean featured;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

//    @OneToOne(mappedBy = "product")
//    private ProductDetail productDetail;
}
