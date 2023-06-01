package com.shop.medicineshop.response.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
private Integer cartId;
private List<CartItemDTO> cartItems;
}
