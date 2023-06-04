package com.shop.medicineshop.service.store;

import com.shop.medicineshop.model.store.StoreProduct;
import com.shop.medicineshop.repository.store.StoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreProductService {
    private final StoreProductRepository storeProductRepository;

    @Autowired
    public StoreProductService(StoreProductRepository storeProductRepository) {
        this.storeProductRepository = storeProductRepository;
    }

//    public StoreProduct getProductInStore(Integer productId, Integer storeId) {
//        return storeProductRepository.findByProductIdAndStoreId(productId, storeId);
//    }
}
