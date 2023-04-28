package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Truy xuất danh sách tất cả các sản phẩm.
     *
     * @return a list of Product objects
     */
    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    /**
     * Truy xuất một sản phẩm có ID được chỉ định.
     *
     * @param id the ID of the product to retrieve
     * @return a ResponseEntity containing the product, if found, or a not found response
     */
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

    /**
     * Truy xuất danh sách các sản phẩm khớp với truy vấn tìm kiếm đã chỉ định.
     *
     * @param query the search query
     * @return a list of Product objects that match the search query
     */
    @GetMapping
    public List<Product> searchProducts(@RequestParam("search") String query) {
        return productService.searchProducts(query);
    }

    /**
     * Thêm một sản phẩm mới vào hệ thống.
     *
     * @param product the new Product object to add
     * @return a ResponseEntity indicating success or failure of the operation
     */
    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Cập nhật một sản phẩm hiện có với ID được chỉ định.
     *
     * @param id the ID of the product to update
     * @param product the updated Product object
     * @return a ResponseEntity indicating success or failure of the operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok().build();
    }

    /**
     * Xóa một sản phẩm có ID được chỉ định.
     *
     * @param ids the list ID of the product to delete
     * @return a ResponseEntity indicating success or failure of the operation
     */
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteProduct(@PathVariable List<Long> ids) {
        productService.deleteProduct(ids);
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

