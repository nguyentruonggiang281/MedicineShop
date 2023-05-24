package com.shop.medicineshop.response.category;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.response.product.ProductDTO;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Builder
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getParentId(),
//                getIdIfNotNull(category.getParentId()),
                category.getDescription(),
                category.getImage(),
                category.isStatus(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt());
    }
//    private Integer getIdIfNotNull(Object entity) {
//
//    }

    public List<CategoryDTO> mapCategoriesToDTO(List<Category> categories) {
        return categories.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
