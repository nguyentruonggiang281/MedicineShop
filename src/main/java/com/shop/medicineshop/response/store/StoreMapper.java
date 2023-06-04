package com.shop.medicineshop.response.store;

import com.shop.medicineshop.model.store.Store;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StoreMapper implements Function<Store, StoreDTO> {
    @Override
    public StoreDTO apply(Store store) {
        return StoreDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .email(store.getEmail())
                .address(store.getAddress())
                .build();
    }

    public StoreDTO map(Store store, double distance) {
        return StoreDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .email(store.getEmail())
                .address(store.getAddress())
                .distance(distance)
                .build();
    }
}
