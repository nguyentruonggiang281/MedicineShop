package com.shop.medicineshop.controller;

import com.shop.medicineshop.request.OrderRequest;
import com.shop.medicineshop.response.order.OrderDTO;
import com.shop.medicineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        OrderDTO orderDTO = orderService.createOrder(request);
        if (orderDTO==null){
            return ResponseEntity.badRequest().body("failed to create order");
        }
        return ResponseEntity.ok().body(orderDTO);
    }
}
