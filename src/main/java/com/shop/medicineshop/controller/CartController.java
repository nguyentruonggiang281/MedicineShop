package com.shop.medicineshop.controller;


import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.cart.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/cart")
public class CartController {
    @PostMapping("/item")
    public ResponseEntity addItem(@RequestBody Map<String, Object> body)
    {
        int idAcc = (int) body.get("idAcc");
        int idProd = (int) body.get("idProd");

        return ResponseEntity.ok().body(null);
    }
}
