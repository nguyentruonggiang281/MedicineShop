package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.ProductDetail;
import com.shop.medicineshop.model.product.Unit;
import com.shop.medicineshop.response.category.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDTO(
        Integer id,
        Integer categoryID,
        String name,
        String slug,

//        List<Unit> units,
//        List<Asset> assets,
        String unit,
        String specifications,
        String asset,
        float price,
        Float discount,
        int quantity,
        Integer sold,
        boolean status,
        boolean featured
//        LocalDateTime createdAt,
//        LocalDateTime updatedAt,
//        LocalDateTime deletedAt
) {

}
