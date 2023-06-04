package com.shop.medicineshop.service;

import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.cart.CartItem;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.repository.cart.CartItemRepository;
import com.shop.medicineshop.repository.cart.CartRepository;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.repository.product.UnitRepository;
import com.shop.medicineshop.request.AddToCartRequest;
import com.shop.medicineshop.request.UpdateCartItemRequest;
import com.shop.medicineshop.response.cart.CartDTO;
import com.shop.medicineshop.response.cart.CartDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private CartDTOMapper cartDTOMapper;

    public CartDTO getAllCartItemByCustomer(Integer id) {
        Optional<Customer> customer = customerRepository.findByAccount_Id(id);
        return customer.map(value -> cartDTOMapper.apply(cartRepository.getCartByCustomer(value))).orElse(null);
    }

    public CartDTO addItem(AddToCartRequest request) {
        Optional<Cart> cart = cartRepository.findByCustomer_Account_Id(request.getIdAccount());
        Product product = productRepository.getProductsById(request.getIdProduct());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = cartItemRepository.findByProduct_IdAndUnit_UnitIdAndCart_Id(request.getIdProduct(), request.getIdUnit(), cart.get().getId());
            if (cartItem.isPresent()) {
                cartItem.get().setQuantity(request.getQuantity());
                cartItem.get().setPrice(request.getPrice());
                cartItemRepository.save(cartItem.get());
            }else {
                CartItem cartItem1 = new CartItem();
                cartItem1.setProduct(product);
                cartItem1.setQuantity(request.getQuantity());
                cartItem1.setPrice(request.getPrice());
                cartItem1.setCart(cart.get());
                cartItem1.setUnit(unitRepository.getUnitByUnitId(request.getIdUnit()));
                cartItemRepository.save(cartItem1);
            }
            return cartDTOMapper.apply(cart.get());
        }

        return null;
    }

    public CartDTO updateItem(UpdateCartItemRequest request) {

        Optional<CartItem> cartItem = cartItemRepository.findCartItemByCartItemId(request.getIdItem());

        if (cartItem.isPresent()) {
            cartItem.get().setUnit(unitRepository.getUnitByUnitId(request.getIdUnit()));
            cartItem.get().setQuantity(request.getQuantity());
            cartItem.get().setPrice(request.getPrice());
            cartItemRepository.save(cartItem.get());
            return cartDTOMapper.apply(cartItem.get().getCart());
        }

        return null;
    }

    public CartDTO deleteItem(Integer idItem) {
            Optional<CartItem> cartItem = cartItemRepository.findById(idItem);
            if (cartItem.isPresent()) {
                cartItemRepository.deleteById(idItem);
                return cartDTOMapper.apply(cartItem.get().getCart());
            }
        return null;
    }
}
