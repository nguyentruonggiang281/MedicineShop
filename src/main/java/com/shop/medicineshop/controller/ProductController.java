package com.shop.medicineshop.controller;


import com.shop.medicineshop.exception.ProductNotFoundException;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/product")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

//    @PutMapping("/product/{id}")
//    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
//        return productRepository.findById(id)
//                .map(product -> {
//                    product.setName(newProduct.getName());
//                    product.setUsedTime(newProduct.getUsedTime());
//                    product.setStatus(newProduct.getStatus());
//                    product.setPrice(newProduct.getPrice());
//                    return productRepository.save(product);
//                }).orElseThrow(() -> new ProductNotFoundException(id));
//    }

    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return  "Product with id "+id+" has been deleted success.";
    }

    @DeleteMapping("/products/{ids}")
    String deleteListProduct(@PathVariable("ids") List<Long> ids){
        ids.forEach(d->{
            if(productRepository.existsById(d)){
                productRepository.deleteById(d);
            }
        });
        return  "Product with id "+ids+" has been deleted success.";
    }
}

