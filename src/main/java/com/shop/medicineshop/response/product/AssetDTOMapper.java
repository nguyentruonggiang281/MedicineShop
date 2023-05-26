package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.Unit;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Builder
public class AssetDTOMapper implements Function<Asset, AssetDTO> {


    @Override
    public AssetDTO apply(Asset asset) {
        return new AssetDTO(
                asset.getAssetId(),
                asset.getFileName(),
                asset.getFilePath(),
//                asset.getFileSize(),
                asset.getFileType()
//                asset.getCreatedAt(),
//                asset.getUpdatedAt(),
//                asset.getDeletedAt()
                );
    }
    public List<AssetDTO> mapAssetsToDTO(List<Asset> products) {
        return products.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
