package com.shop.medicineshop.response.cart;

import com.shop.medicineshop.model.cart.CartItem;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Builder
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

    @Override
    public CartItemDTO apply(CartItem cartItem) {
        return  new CartItemDTO(
                cartItem.getCartItemId(),
                cartItem.getProduct().getName(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getUnit().getName());
    }

    public List<CartItemDTO> mapCartItemToDTO(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
