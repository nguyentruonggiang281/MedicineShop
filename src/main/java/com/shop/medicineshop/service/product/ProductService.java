package com.shop.medicineshop.service.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import com.shop.medicineshop.repository.product.ProductDetailRepository;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.repository.product.TagRepository;
import com.shop.medicineshop.response.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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


    public List<UnitDTO> getProductQuantityPerUnit(Integer productId) {
        Optional<Product> p = productRepository.findById(productId);
        List<UnitDTO> unitDTOs = new ArrayList<>();
        if(p.isPresent()) {
            float basePrice = p.get().getPrice();
            int baseQuantity = p.get().getQuantity();
            int baseSpecifications = 1;
            List<Unit> units = p.get().getUnits();
            for(int rank = units.size() - 1; rank >= 0; rank--) {
                Unit u = units.get(rank);
                UnitDTO unitDTO = new UnitDTO(u.getUnitId() ,u.getName(), rank, basePrice * baseSpecifications, baseQuantity / baseSpecifications,u.getSpecifications());
                baseSpecifications *= u.getSpecifications();
                unitDTOs.add(unitDTO);
            }
            return unitDTOs;
        }
        return null;
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
