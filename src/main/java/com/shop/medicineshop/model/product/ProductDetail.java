package com.shop.medicineshop.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "brand")
    private String brand;
    @Column(name = "dosage_form")
    private String dosageForm;
    @Column(name = "origin")
    private String origin;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column (name = "short_description",columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "ingredients",columnDefinition = "TEXT")
    private String ingredients;
    @Column(name = "utilization",columnDefinition = "TEXT")//công dụng thay cho usage, usage là từ khóa trong sql
    private String utilization;
    @Column(name = "dosage", columnDefinition = "TEXT")
    private String dosage;
    @Column(name = "adverse_effects", columnDefinition = "TEXT")
    private String adverseEffects;
    @Column(name = "careful", columnDefinition = "TEXT")
    private String careful;
    @Column(name = "preservation", columnDefinition = "TEXT")
    private String preservation;
}
