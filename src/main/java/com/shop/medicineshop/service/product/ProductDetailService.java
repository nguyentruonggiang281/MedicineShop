package com.shop.medicineshop.service.product;

import com.shop.medicineshop.model.product.ProductDetail;
import com.shop.medicineshop.repository.product.ProductDetailRepository;
import com.shop.medicineshop.response.product.ProductDetailDTO;
import com.shop.medicineshop.response.product.ProductDetailDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductDetailDTOMapper productDetailDTOMapper;

//    public void save(ProductDetail productDetail) {
//        productDetailRepository.save(productDetail);
//    }

    //    truy xuất chi tiết sản phẩm
    public ProductDetailDTO getProductDetails(Integer id) {
        return productDetailDTOMapper.apply(productDetailRepository.getProductDetailByProductId(id));
    }
}
