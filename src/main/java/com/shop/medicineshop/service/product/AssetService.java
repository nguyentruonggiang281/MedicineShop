package com.shop.medicineshop.service.product;

import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.repository.product.AssetRepository;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.response.product.AssetDTO;
import com.shop.medicineshop.response.product.AssetDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AssetDTOMapper assetDTOMapper;

    public void save(Asset asset) {
        assetRepository.save(asset);
    }


    public List<AssetDTO> getProductAssets(Integer id) {
        List<AssetDTO> assets = new ArrayList<>();
        for (Asset asset : assetRepository.findByProduct_Id(id)) {
            assets.add(assetDTOMapper.apply(asset));
        }
        return assets;
    }
}
