package com.shop.medicineshop.response.store;

import com.shop.medicineshop.model.address.Address;
import lombok.Builder;

import java.awt.geom.Point2D;

@Builder
public record StoreDTO(
        Integer storeId,
        String name,
        String email,
        Address address,
        Double distance //km
) {

}
