package com.shop.medicineshop.controller;

import com.shop.medicineshop.request.StoreRequest;
import com.shop.medicineshop.response.order.OrderDTO;
import com.shop.medicineshop.response.store.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.medicineshop.service.store.StoreService;

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
        if (re != null)
            return ResponseEntity.ok().body(re);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idStore}/orders")
    public ResponseEntity<?> getAllOrderByStore(@PathVariable("idStore") Integer idStore) {
        List<OrderDTO> re = storeService.getAllOrderByStore(idStore);
        if (re != null && re.size() > 0)
            return ResponseEntity.ok().body(re);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idStore}/products")
    public ResponseEntity<?> getProductsInStore(@PathVariable("idStore") Integer idStore) {
//        to do
        return null;
    }

    @PostMapping("/{idStore}/products/{idProduct}/{quantity}")
    public ResponseEntity<?> createProductInStore(@PathVariable("idStore") Integer idStore,
                                                  @PathVariable("idProduct") Integer idProduct,
                                                  @PathVariable("quantity") Integer quantity){
        return ResponseEntity.ok().body(storeService.addOrUpdateProduct(idStore, idProduct, quantity));
    }

    @PutMapping("/{idStore}/products/{idProduct}/{quantity}")
    public ResponseEntity<?> updateProductInStore(@PathVariable("idStore") Integer idStore,
                                                  @PathVariable("idProduct") Integer idProduct,
                                                  @PathVariable("quantity") Integer quantity) {// cộng thêm một lượng quantity
        return ResponseEntity.ok().body(storeService.addOrUpdateProduct(idStore, idProduct, quantity));
    }

    @GetMapping("/{idStore}/products/{idProduct}")
    public ResponseEntity<?> getProductInStore(@PathVariable("idStore") Integer idStore,
                                               @PathVariable("idProduct") Integer idProduct) {
//        to do
        return null;
    }

}
