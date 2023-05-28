package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import com.shop.medicineshop.response.category.CategoryDTO;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Builder
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {

        return new ProductDTO(
                product.getId(),
                getIdIfNotNull(product.getCategory()),
                product.getName(),
                product.getSlug(),
                getUnit(product),
                getSpecifications(product),
                getAsset(product),
                product.getPrice(),
                product.getDiscount(),
                product.getQuantity(),
                product.getSold(),
                product.isStatus(),
                product.isFeatured());
    }

    public String getUnit(Product product) {
        String unit = "";
        if (product.getUnits() != null) {
            for (Unit u : product.getUnits()) {
                    unit = u.getName();
            }
        }
        return unit;
    }

    public String getSpecifications(Product product) {
        StringBuilder specifications = new StringBuilder();
        if (product.getUnits() != null) {
            for (Unit units : product.getUnits()) {
                 if (units.getRank() == 0) {
                    specifications = new StringBuilder(units.getName());
                } else {
                    specifications.append(" x ").append(units.getSpecifications()).append(" ").append(units.getName());
                }
            }
        }
        return specifications.toString();
    }

    public String getAsset(Product product) {
        if (product.getAssets() == null) {
            return "";
        }
        return product.getAssets().get(0).getFilePath();
    }

    public Integer getIdIfNotNull(Object entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            Integer id = (Integer) getIdMethod.invoke(entity);
            return id;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProductDTO> mapProductsToDTO(List<Product> products) {
        return products.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }

}