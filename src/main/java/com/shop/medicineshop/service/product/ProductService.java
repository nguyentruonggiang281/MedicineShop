package com.shop.medicineshop.service.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.repository.product.ProductDetailRepository;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.repository.product.TagRepository;
import com.shop.medicineshop.response.product.ProductDTO;
import com.shop.medicineshop.response.product.ProductDTOMapper;
import com.shop.medicineshop.response.product.ProductDetailDTO;
import com.shop.medicineshop.response.product.ProductDetailDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProductDTOMapper productDTOMapper;

    // lấy tất cả sản phẩm
    public List<ProductDTO> getAllProducts() {
        return productDTOMapper.mapProductsToDTO(productRepository.findAll());
    }

//    lấy sản phẩm theo id
    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.getProductsById(id);
        return productDTOMapper.apply(product);
    }

//    tìm kiếm sản phẩm
    public List<ProductDTO> searchProducts(String query) {
        return productDTOMapper.mapProductsToDTO( productRepository.findByNameContaining(query));
    }

    public List<ProductDTO> getProductsByTag(String tagSlug) {

        return productDTOMapper.mapProductsToDTO(productRepository.findByTags_Id(tagRepository.findBySlug(tagSlug).getId()));
    }


    public List<ProductDTO> getProductsByCategoryId(Integer categoryId) {

        return productDTOMapper.mapProductsToDTO(productRepository.findByCategoryId(categoryId));
    }




//    thêm 1 sản phẩm
    public void addProduct(Product product) {
        productRepository.save(product);
    }

//    update sản phẩm
    public void updateProduct(Integer id, Product product) {
//        Product optionalProduct = getProductById(id);
//        if (optionalProduct.isPresent()) {
//            Product existingProduct = optionalProduct.get();
//            existingProduct.setName(product.getName());
//            existingProduct.setPrice(product.getPrice());
//            // Cập nhật các thuộc tính khác của product
//            productRepository.save(existingProduct);
//        }
    }

//    xóa sản phẩm
    public void deleteProduct(List<Long> ids) {
//        ids.forEach(id -> {
//            if (productRepository.existsById(id)) {
//                productRepository.deleteById(id);
//            }
//        });
//        productRepository.deleteById(id);
    }


}
