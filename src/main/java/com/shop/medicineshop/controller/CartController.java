package com.shop.medicineshop.controller;


import com.shop.medicineshop.request.AddToCartRequest;
import com.shop.medicineshop.request.UpdateCartItemRequest;
import com.shop.medicineshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

//    @GetMapping
    @PostMapping("/add-item")
    public ResponseEntity<?> addItem(@RequestBody AddToCartRequest request) {
        if (cartService.addItem(request)==null){
            return ResponseEntity.badRequest().body("failed to add to cart");
        }
        return ResponseEntity.ok().body(cartService.addItem(request));
    }

    @PutMapping(" ")
    public ResponseEntity<?> updateItem( @RequestBody UpdateCartItemRequest request) {
        if (cartService.updateItem( request) == null){
            return ResponseEntity.badRequest().body("failed to update cart");
        }
        return ResponseEntity.ok().body(cartService.updateItem( request));
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer idItem) {
        if (cartService.deleteItem(idItem) == null){
            return ResponseEntity.badRequest().body("failed to delete cart");
        }
        return ResponseEntity.ok().body(cartService.deleteItem(idItem));
    }
}
