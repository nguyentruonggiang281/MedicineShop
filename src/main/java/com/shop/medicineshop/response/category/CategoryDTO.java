package com.shop.medicineshop.response.category;

import java.time.LocalDateTime;
import java.util.Date;

public record CategoryDTO(
        Integer id,
        String name,
        String slug,
        Integer parentId,
        String description,
        String image,
        boolean status,
        LocalDateTime createdAt,
        Date updatedAt,
        Date deletedAt

) {
}
