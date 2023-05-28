package com.shop.medicineshop.controller;

import com.shop.medicineshop.response.cart.CartDTO;
import com.shop.medicineshop.service.CartService;
import com.shop.medicineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;

    @GetMapping("/{idAccount}/carts")
    public ResponseEntity<?> getAllCartItemByCustomer(@PathVariable Integer idAccount) {
        {   if (cartService.getAllCartItemByCustomer(idAccount) != null)
            return ResponseEntity.ok().body(cartService.getAllCartItemByCustomer(idAccount));
    }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found account");
    }

}
