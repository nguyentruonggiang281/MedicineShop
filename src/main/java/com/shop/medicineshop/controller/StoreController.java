package com.shop.medicineshop.controller;

import com.shop.medicineshop.request.StoreRequest;
import com.shop.medicineshop.response.store.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.medicineshop.service.StoreService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/store")
public class StoreController {
    @Autowired
    StoreService storeService;

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreRequest storeRequest) {
        if (storeService.createStore(storeRequest)) return ResponseEntity.ok().body("success");
        return ResponseEntity.badRequest().body("failed to create store");
    }

    @GetMapping("/getTop{maxResults}StoresNearest/{idAddress}")
    public ResponseEntity<?> getStoresNearestInSameProvince(@PathVariable("idAddress") Integer idAddress,
                                                            @PathVariable("maxResults") Integer maxResults) {
        List<StoreDTO> re = storeService.getStoresNearest(idAddress, maxResults);
        if(re != null)
            return ResponseEntity.ok().body(re);
        return ResponseEntity.notFound().build();
    }
}
