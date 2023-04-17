package com.shop.medicineshop.controller;


import com.shop.medicineshop.exception.ProductNotFoundException;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.reponsitory.ProductRepository;
import com.shop.medicineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}










//@RestController
//@RequestMapping("/dashboard")
//@CrossOrigin("http://localhost:3000")
//public class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @PostMapping("/product")
//    Product newProduct(@RequestBody Product newProduct) {
//        return productRepository.save(newProduct);
//    }
//
//    @GetMapping("/product")
//    List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @GetMapping("/product/{id}")
//    Product getProductById(@PathVariable Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException(id));
//    }
//
////    @PutMapping("/product/{id}")
////    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
////        return productRepository.findById(id)
////                .map(product -> {
////                    product.setName(newProduct.getName());
////                    product.setUsedTime(newProduct.getUsedTime());
////                    product.setStatus(newProduct.getStatus());
////                    product.setPrice(newProduct.getPrice());
////                    return productRepository.save(product);
////                }).orElseThrow(() -> new ProductNotFoundException(id));
////    }
//
//    @DeleteMapping("/product/{id}")
//    String deleteProduct(@PathVariable Long id){
//        if(!productRepository.existsById(id)){
//            throw new ProductNotFoundException(id);
//        }
//        productRepository.deleteById(id);
//        return  "Product with id "+id+" has been deleted success.";
//    }
//
//    @DeleteMapping("/products/{ids}")
//    String deleteListProduct(@PathVariable("ids") List<Long> ids){
//        ids.forEach(d->{
//            if(productRepository.existsById(d)){
//                productRepository.deleteById(d);
//            }
//        });
//        return  "Product with id "+ids+" has been deleted success.";
//    }
//}

