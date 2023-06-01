package com.shop.medicineshop.response.cart;

import com.shop.medicineshop.model.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Integer cartItemId;
    private String productName;
    private Integer quantity;
    private Float totalPrice;
    private String unit;

}
