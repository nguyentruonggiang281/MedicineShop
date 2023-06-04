package com.shop.medicineshop.response.store;

import lombok.Builder;

@Builder
public record StoreProductDTO(
    int storeId,
    int productId,
    int quantity
) {
}
