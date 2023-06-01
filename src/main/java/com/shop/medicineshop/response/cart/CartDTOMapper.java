package com.shop.medicineshop.response.cart;

import com.shop.medicineshop.model.cart.Cart;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
@Service
@Builder
public class CartDTOMapper implements Function<Cart, CartDTO> {
    @Autowired
    private CartItemDTOMapper cartItemDTOMapper;

    @Override
    public CartDTO apply(Cart cart) {
        List<CartItemDTO> cartItemDTO = cartItemDTOMapper.mapCartItemToDTO(cart.getCartItems());
        return new CartDTO(cart.getId(), cartItemDTO);
    }
}
