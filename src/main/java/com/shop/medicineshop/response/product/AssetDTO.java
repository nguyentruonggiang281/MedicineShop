package com.shop.medicineshop.response.product;

import java.time.LocalDateTime;

public record AssetDTO(Integer assetId,

                       String fileName,

                       String filePath,

//                       Integer fileSize,

                       String fileType//media, img,...


//                       LocalDateTime createdAt,


//                       LocalDateTime updatedAt,


//                       LocalDateTime deletedAt
) {
}
