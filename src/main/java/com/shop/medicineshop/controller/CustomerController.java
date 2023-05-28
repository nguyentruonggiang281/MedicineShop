package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.cart.CartItem;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.response.customer.CustomerDTO;
import com.shop.medicineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {
//        customer.setCart(new Cart(0,new ArrayList<CartItem>(), LocalDateTime.now(), null));
        System.out.println(customer.name() + " " + customer.email() + " " + customer.phoneNumber() + " " + customer.addressId() + " " + customer.idAcc());
//        Customer savedCustomer = customerService.saveCustomer(customer);
        if(customer != null){
            return ResponseEntity.ok().body(null);
        }else{
            return ResponseEntity.badRequest().body(null);
        }

    }
}
