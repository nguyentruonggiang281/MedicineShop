package com.shop.medicineshop.service;

import com.shop.medicineshop.mapper.ItemMapper;
import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.cart.CartItem;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.order.Order;

import com.shop.medicineshop.model.order.OrderItem;
import com.shop.medicineshop.model.order.OrderStatus;
import com.shop.medicineshop.model.store.Store;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.repository.cart.CartItemRepository;
import com.shop.medicineshop.repository.order.OrderItemRepository;
import com.shop.medicineshop.repository.order.OrderRepository;
import com.shop.medicineshop.repository.store.StoreRepository;
import com.shop.medicineshop.request.OrderRequest;
import com.shop.medicineshop.response.order.OrderDTO;
import com.shop.medicineshop.response.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ItemMapper itemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartService cartService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public OrderDTO createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Optional<Customer> cus = customerRepository.findByAccount_Id(orderRequest.getIdAccount());
        Optional<Address> address = addressRepository.findById(orderRequest.getIdAddress());
        Optional<Store> store = storeRepository.findById(orderRequest.getIdStore());
        order.setStatus(OrderStatus.CONFIRMING);
        if(cus.isPresent() && address.isPresent() && store.isPresent() && orderRequest.getIdCartItems() != null && orderRequest.getIdCartItems().size() > 0) {
            order.setCustomer(cus.get());
            order.setAddress(address.get());
            order.setStore(store.get());
        }else {
            return null;
        }
        List<OrderItem> items = new ArrayList<>();

        float orderPrice = 0;
        for(Integer idItem : orderRequest.getIdCartItems()) {
            Optional<CartItem> cartItem = cartItemRepository.findById(idItem);
            if(cartItem.isPresent()) {
                OrderItem oItem= itemMapper.toOrderItem(cartItem.get());
                orderItemRepository.save(oItem);
                orderPrice += oItem.getPrice();
                items.add(oItem);
                cartItemRepository.deleteByCartItemId(idItem);
            }else {
                return null;
            }
        }
        order.setTotal(orderPrice);
        order.setCreatedAt(LocalDateTime.now());
        order.setShippingFee(orderRequest.getShippingFee());
        order.setOrderItems(items);
        return orderMapper.apply(orderRepository.save(order));
    }
}
