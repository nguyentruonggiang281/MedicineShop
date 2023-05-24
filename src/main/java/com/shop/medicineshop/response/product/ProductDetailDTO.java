package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import jakarta.persistence.Column;

import java.util.List;

public record ProductDetailDTO(
        Integer id,
        Integer categoryID,
        String name,
        String slug,
        List<UnitDTO> units,
        List<AssetDTO> assets,
        String unit,
        String specifications,
        String asset,
        float price,
        Float discount,
        int quantity,
        Integer sold,
        boolean status,
        boolean featured,
        String brand,
        String dosageForm,
         String origin,
         String manufacturer,
         String shortDescription,
         String description,
         String ingredients,//công dụng thay cho usage, usage là từ khóa trong sql
         String utilization,
         String dosage,
         String adverseEffects,
         String careful,
         String preservation

) {
}
