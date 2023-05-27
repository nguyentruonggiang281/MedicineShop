package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.ProductDetail;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
@Service
@Builder
public class ProductDetailDTOMapper implements Function<ProductDetail, ProductDetailDTO> {
    @Autowired
    private ProductDTOMapper productDTOMapper;
@Autowired
private AssetDTOMapper assetDTOMapper;
    @Autowired
    private UnitDTOMapper unitDTOMapper;
    @Override
    public ProductDetailDTO apply(ProductDetail productDetail) {
        Product product = productDetail.getProduct();
        List<AssetDTO> assetDTOS = assetDTOMapper.mapAssetsToDTO(product.getAssets());
        List<UnitDTO> unitDTOS = unitDTOMapper.mapUnitsToDTO(product.getUnits());
       ProductDTO productDTO = productDTOMapper.apply(product);
        return new ProductDetailDTO(
                productDTO.id(),
                product.getCategory().getName(),
                productDTO.name(),
                productDTO.slug(),
                unitDTOS,
                assetDTOS,
                productDTO.unit(),
                productDTO.specifications(),
//                productDTO.asset(),
                productDTO.price(),
                productDTO.discount(),
                productDTO.quantity(),
                productDTO.sold(),
                productDTO.status(),
                productDTO.featured(),
                productDetail.getBrand(),
                productDetail.getDosageForm(),
                productDetail.getOrigin(),
                productDetail.getManufacturer(),
                productDetail.getShortDescription(),
                productDetail.getDescription(),
                productDetail.getIngredients(),
                productDetail.getUtilization(),
                productDetail.getDosage(),
                productDetail.getAdverseEffects(),
                productDetail.getCareful(),
                productDetail.getPreservation()
        );
    }
}
