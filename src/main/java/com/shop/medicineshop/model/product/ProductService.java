package com.shop.medicineshop.model.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContaining(query);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = getProductById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            // Cập nhật các thuộc tính khác của product
            productRepository.save(existingProduct);
        }
    }

    public void deleteProduct(List<Long> ids) {
        ids.forEach(id -> {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            }
        });
//        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
