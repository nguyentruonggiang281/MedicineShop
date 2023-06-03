package com.shop.medicineshop.controller;

import com.shop.medicineshop.request.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.medicineshop.service.StoreService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/store")
public class StoreController {
    @Autowired
    StoreService storeService;

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreRequest storeRequest) {
        if (storeService.createStore(storeRequest))
            return ResponseEntity.ok().body("success");
        return ResponseEntity.badRequest().body("failed to create store");
    }
}
